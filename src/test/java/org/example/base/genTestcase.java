package org.example.base;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class genTestcase {

//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void createDataset(String xmlFilePath, String url, String method, String testcaseFilePath) throws ParserConfigurationException, SAXException, IOException {
        // Đọc tệp XML và tạo test data
        url = "http://localhost:8100" + url;
        File xmlFile = new File(xmlFilePath);
        if (!xmlFile.exists()) {
            System.err.println("File not found: " + xmlFilePath);
            return;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        // Lấy danh sách các tham số
        NodeList parameterList = document.getElementsByTagName("parameter");

        List<String> pnameList = new ArrayList<>();
        List<String> ptypeList = new ArrayList<>();

        for (int i = 0; i < parameterList.getLength(); i++) {
            Element parameter = (Element) parameterList.item(i);
            String pname = parameter.getElementsByTagName("name").item(0).getTextContent();
            String ptype = parameter.getElementsByTagName("type").item(0).getTextContent();

            pnameList.add(pname);
            ptypeList.add(ptype);
        }

        // Trích xuất các điều kiện
        NodeList logicList = document.getElementsByTagName("logic");
        Set<String> conditionsSet = new HashSet<>();

        for (int i = 0; i < logicList.getLength(); i++) {
            Element logicElement = (Element) logicList.item(i);
            NodeList caseList = logicElement.getElementsByTagName("case");

            for (int j = 0; j < caseList.getLength(); j++) {
                Element caseElement = (Element) caseList.item(j);

                // Lấy điều kiện chính
                String condition = caseElement.getElementsByTagName("condition").item(0).getTextContent();
                conditionsSet.add(condition);

                // Lấy các điều kiện con
                NodeList subConditionList = caseElement.getElementsByTagName("subCondition");
                for (int k = 0; k < subConditionList.getLength(); k++) {
                    Element subConditionElement = (Element) subConditionList.item(k);
                    String subCondition = subConditionElement.getElementsByTagName("condition").item(0).getTextContent();
                    conditionsSet.add(subCondition);
                }
            }
        }

        // Phân tích các điều kiện
        Map<String, List<String>> paramConditionsValue = new HashMap<>();
        Map<String, List<String>> NormValue = new HashMap<>();
        Map<String, List<String>> EquivalencePartitioningValue = new HashMap<>();

        for (String condition : conditionsSet) {
            if (condition.contains("&&")) {
                String[] conditionParts = condition.split("&&");
                int min = -2147483648;
                int max = 2147483647;

                for (String part : conditionParts) {
                    part = part.trim();
                    String[] tokens = part.split("\\s+");

                    if (tokens.length == 3) {
                        String operator = tokens[1];
                        int value = Integer.parseInt(tokens[2]);

                        switch (operator) {
                            case ">":
                                min = value;
                                break;
                            case ">=":
                                min = value;
                                break;
                            case "<":
                                max = value;
                                break;
                            case "<=":
                                max = value;
                                break;

                        }
                    }
                }

                int normValue =  generateNormInt(min, max);
                int randomValue = generateRandomIntInRange(min, max);

                String paramName1 = conditionParts[0].trim().split("\\s+")[0];

                NormValue.putIfAbsent(paramName1, new ArrayList<>());
                NormValue.get(paramName1).add(String.valueOf(normValue));

                EquivalencePartitioningValue.putIfAbsent(paramName1, new ArrayList<>());
                EquivalencePartitioningValue.get(paramName1).add(String.valueOf(randomValue));
            }

            if (!condition.contains("&&")) {

                int min = -2147483648;
                int max = 2147483647;
                String[] conditionParts = condition.split("&&");
                for (String part : conditionParts) {
                    part = part.trim();
                    String[] tokens = part.split("\\s+");

                    if (tokens.length >= 3) {
                        String operator = tokens[1];
                        int value = Integer.parseInt(tokens[2]);
                        switch (operator) {
                            case ">":
                                String paramName1 = conditionParts[0].trim().split("\\s+")[0];
                                paramConditionsValue.putIfAbsent(paramName1, new ArrayList<>());
                                paramConditionsValue.get(paramName1).add(String.valueOf(max));

                                int normValue =  generateNormInt(value, max);

                                NormValue.putIfAbsent(paramName1, new ArrayList<>());
                                NormValue.get(paramName1).add(String.valueOf(normValue));

                                int randomValue = generateRandomIntInRange (value, max);

                                EquivalencePartitioningValue.putIfAbsent(paramName1, new ArrayList<>());
                                EquivalencePartitioningValue.get(paramName1).add(String.valueOf(randomValue));
                                break;
                            case ">=":
                                String paramName2 = conditionParts[0].trim().split("\\s+")[0];
                                paramConditionsValue.putIfAbsent(paramName2, new ArrayList<>());
                                paramConditionsValue.get(paramName2).add(String.valueOf(max));
                                int normValue2 =  generateNormInt(value, max);

                                NormValue.putIfAbsent(paramName2, new ArrayList<>());
                                NormValue.get(paramName2).add(String.valueOf(normValue2));

                                int randomValue2 = generateRandomIntInRange (value, max);

                                EquivalencePartitioningValue.putIfAbsent(paramName2, new ArrayList<>());
                                EquivalencePartitioningValue.get(paramName2).add(String.valueOf(randomValue2));
                                break;
                            case "<":
                                String paramName3 = conditionParts[0].trim().split("\\s+")[0];
                                paramConditionsValue.putIfAbsent(paramName3, new ArrayList<>());
                                paramConditionsValue.get(paramName3).add(String.valueOf(min));
                                int normValue3 =  generateNormInt(min, value);

                                NormValue.putIfAbsent(paramName3, new ArrayList<>());
                                NormValue.get(paramName3).add(String.valueOf(normValue3));

                                int randomValue3 = generateRandomIntInRange (min, value);

                                EquivalencePartitioningValue.putIfAbsent(paramName3, new ArrayList<>());
                                EquivalencePartitioningValue.get(paramName3).add(String.valueOf(randomValue3));
                                break;
                            case "<=":
                                String paramName4 = conditionParts[0].trim().split("\\s+")[0];
                                paramConditionsValue.putIfAbsent(paramName4, new ArrayList<>());
                                paramConditionsValue.get(paramName4).add(String.valueOf(min));
                                int normValue4 =  generateNormInt(min, value);

                                NormValue.putIfAbsent(paramName4, new ArrayList<>());
                                NormValue.get(paramName4).add(String.valueOf(normValue4));

                                int randomValue4 = generateRandomIntInRange (min, value);

                                EquivalencePartitioningValue.putIfAbsent(paramName4, new ArrayList<>());
                                EquivalencePartitioningValue.get(paramName4).add(String.valueOf(randomValue4));
                                break;
                            case "=":
                                String paramName5 = conditionParts[0].trim().split("\\s+")[0];
                                paramConditionsValue.putIfAbsent(paramName5, new ArrayList<>());
                                paramConditionsValue.get(paramName5).add(String.valueOf(min));
                        }
                    }
                }
            }

            String[] conditionParts = condition.split("&&");
            for (String part : conditionParts) {
                part = part.trim();
                if (part.equalsIgnoreCase("Khác")) {
                    continue;
                }

                String[] tokens = part.split("\\s+");
                if (tokens.length >= 3) {
                    String paramName = tokens[0];
                    String value = tokens[2];

                    paramConditionsValue.putIfAbsent(paramName, new ArrayList<>());
                    paramConditionsValue.get(paramName).add(value);
                }
            }
        }
        Map<String, List<String>> boundaryValues = generateTestData(pnameList, ptypeList,  paramConditionsValue);
        System.out.println("boundaryValues: " + boundaryValues);
        System.out.println("NormValue: " + NormValue);
        System.out.println("EquivalencePartitioningValue: " + EquivalencePartitioningValue);
        //       Map<String, List<String>> mergedParamValues = mergeParamValues(boundaryValues, NormValue);
        //       System.out.println("mergedParamValues: " + mergedParamValues);
        //       combineMaps(boundaryValues,NormValue);
        List<Map<String, String>> testDataList = new ArrayList<>();
        for (String key1 : boundaryValues.keySet()) {
            // Tạo map mới cho từng key của map1
            Map<String, List<String>> combinedMap = new HashMap<>();
            combinedMap.put(key1, boundaryValues.get(key1));
            System.out.println("\n");
            System.out.println("combinedMap1 : " + combinedMap);
            // Duyệt qua các key của map2 và thêm vào combinedMap nếu khác với key1
            for (String key2 : NormValue.keySet()) {
                if (!key2.equals(key1)) {
                    System.out.println(key1 + " " + key2);
                    combinedMap.put(key2, NormValue.get(key2));
                    System.out.println("combinedMap2 : " + combinedMap);

                }

            }
            generateCombinations(pnameList, combinedMap, new HashMap<>(), testDataList);
            // Hiển thị map kết hợp cho key1

        }
        generateCombinations(pnameList, EquivalencePartitioningValue, new HashMap<>(), testDataList);

        // Gọi API và ghi kết quả
        callApiWithGeneratedData(url, method, testcaseFilePath, testDataList, pnameList);
    }

    public static Map<String, List<String>> generateTestData(List<String> pnameList, List<String> ptypeList, Map<String, List<String>>  paramConditionsValue) {
        Map<String, List<String>> boundaryValues = new HashMap<>();

        for (int i = 0; i < pnameList.size(); i++) {
            String pname = pnameList.get(i);
            List<String> conditions =  paramConditionsValue.getOrDefault(pname, new ArrayList<>());

            List<String> values = generateValuesFromConditions(conditions);
            boundaryValues.put(pname, values);
        }
        return boundaryValues;
    }

    public static List<String> generateValuesFromConditions(List<String> conditions) {
        Set<String> bien = new HashSet<>();

        for (String condition : conditions) {
            try {
                int value = Integer.parseInt(condition);
                long[] testValues = getEquivalencePartitionValuesInt(value);
                for (long v : testValues) {
                    bien.add(String.valueOf(v));
                }
            } catch (NumberFormatException e) {
                // Ignore if not a number
            }

        }
        return new ArrayList<>(bien);
    }

    public static long[] getEquivalencePartitionValuesInt(int value) {
        return new long[]{
                (long) value - 1, // Ép kiểu về long để tránh tràn số
                (long) value,     // Ép kiểu về long
                (long) value + 1  // Ép kiểu về long
        };
    }


    public static void generateCombinations(List<String> params, Map<String, List<String>> boundaryValues, Map<String, String> currentCombination, List<Map<String, String>> testDataList) {
        if (params.isEmpty()) {
            testDataList.add(new HashMap<>(currentCombination));
            return;
        }

        String currentParam = params.get(0);
        List<String> remainingParams = params.subList(1, params.size());

        List<String> values = boundaryValues.getOrDefault(currentParam, Collections.singletonList(""));

        for (String value : values) {
            currentCombination.put(currentParam, value);
            generateCombinations(remainingParams, boundaryValues, currentCombination, testDataList);
            currentCombination.remove(currentParam);
        }
    }

    public static int  generateNormInt(int min, int max) {
        if (min == max) {
            return min;
        }
        return (int) (((long) min + (long) max) / 2); // Không cần Math.round()
    }





    public static void callApiWithGeneratedData(String url, String method, String testcaseFilePath, List<Map<String, String>> testDataList, List<String> pnameList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testcaseFilePath, true))) {
            for (Map<String, String> testData : testDataList) {
                StringBuilder requestParam = new StringBuilder();
                for (String pname : pnameList) {
                    String value = testData.getOrDefault(pname, "");
                    requestParam.append(pname).append("=").append(value).append("&");
                }

                String requestParamOrBody = requestParam.substring(0, requestParam.length() - 1); // Xóa dấu & cuối

                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");

                Response response;
                if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
                    request.body(requestParamOrBody);
                } else {
                    for (String param : requestParamOrBody.split("&")) {
                        String[] keyValue = param.split("=");
                        request.param(keyValue[0], keyValue[1]);
                    }
                }

                switch (method.toUpperCase()) {
                    case "POST":
                        response = request.post(url);
                        break;
                    case "GET":
                        response = request.get(url);
                        break;
                    case "PUT":
                        response = request.put(url);
                        break;
                    case "DELETE":
                        response = request.delete(url);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported method: " + method);
                }

                int actualHttpCode = response.getStatusCode();
                String actualResponseBody = response.getBody().asString();

                writer.write(requestParamOrBody + "|" + actualHttpCode + "|" + actualResponseBody);
                writer.newLine(); // Xuống dòng sau mỗi test case
            }
        } catch (IOException e) {
            System.err.println("Error writing to testcase file: " + e.getMessage());
        }
    }

    public static Map<String, List<String>> mergeParamValues(Map<String, List<String>> paramValues, Map<String, List<String>> additionalParamValues) {
        Map<String, List<String>> mergedValues = new HashMap<>(paramValues);

        for (Map.Entry<String, List<String>> entry : additionalParamValues.entrySet()) {
            String param = entry.getKey();
            List<String> additionalValues = entry.getValue();

            if (mergedValues.containsKey(param)) {
                mergedValues.get(param).addAll(additionalValues);
            } else {
                mergedValues.put(param, additionalValues);
            }
        }

        return mergedValues;
    }
    // Hàm generate giá trị random trong khoảng [min, max]
    public static int generateRandomIntInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min phải nhỏ hơn hoặc bằng max");
        }

        // Nếu min và max bằng nhau, trả về min (chỉ có 1 giá trị trong khoảng)
        if (min == max) {
            return min;
        }

        // Sử dụng ThreadLocalRandom để sinh số ngẫu nhiên trong khoảng [min, max]
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    public static void main(String[] args) {
        try {
            // Thay đổi các giá trị bên dưới cho phù hợp với tệp XML, URL, phương thức HTTP và tệp test case của bạn
            String xmlFilePath = "fare_requirement.xml";
            String url = "/api/fare";
            String method = "GET";
            String testcaseFilePath = "16102024.txt";
            createDataset(xmlFilePath, url, method, testcaseFilePath);

            System.out.println("Test case generation and API calls completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

