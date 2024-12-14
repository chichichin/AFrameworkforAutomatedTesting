package org.example.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashSet;
import java.util.Set;

public class ApiTestWithGenDataUlti {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void callApi(String url, String method, String testcaseFilePath, String reportFilePath) {
        int passCount = 0;
        int failCount = 0;
        url = "http://localhost:8200" + url;
        Set<String> uniqueTestCases = new HashSet<>(); // Set để lưu các test case đã xử lý

        try (BufferedReader testcaseReader = new BufferedReader(new FileReader(testcaseFilePath));
             FileWriter report = new FileWriter(reportFilePath)) {

            // Bắt đầu ghi tiêu đề cho báo cáo HTML
            report.write("<html><head><title>API Test Report</title></head><body>");
            report.write("<h1>API Test Report</h1>");
            report.write("<table border='1' cellpadding='10'><tr><th>Test Case</th><th>Expected</th><th>Actual</th><th>Result</th></tr>");

            String testcaseLine;
            while ((testcaseLine = testcaseReader.readLine()) != null) {
                if (!uniqueTestCases.add(testcaseLine)) {
                    // Bỏ qua nếu test case đã tồn tại trong Set
                    continue;
                }

                String[] testcaseParts = testcaseLine.split("\\|");

                if (testcaseParts.length != 3) {
                    System.err.println("Invalid line format in the testcase file: " + testcaseLine);
                    continue;
                }

                String requestParamOrBody = testcaseParts[0];
                int expectedHttpCode = Integer.parseInt(testcaseParts[1]);
                String expectedResponseBody = testcaseParts[2];

                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");

                if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
                    if (!requestParamOrBody.isEmpty()) {
                        for (String param : requestParamOrBody.split("&")) {
                            String[] keyValue = param.split("=");
                            request.param(keyValue[0], keyValue[1]);
                        }
                    }
                } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
                    if (!requestParamOrBody.isEmpty()) {
                        request.body(requestParamOrBody);
                    }
                }

                Response response;
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

                // So sánh kết quả mong đợi và thực tế
                String result;
                boolean isMatch = false;
                if (actualHttpCode == expectedHttpCode) {
                    JsonNode expectedJson = OBJECT_MAPPER.readTree(expectedResponseBody);
                    JsonNode actualJson = OBJECT_MAPPER.readTree(actualResponseBody);

                    // Loại bỏ trường 'timestamp' nếu có
                    if (expectedJson instanceof ObjectNode) {
                        ((ObjectNode) expectedJson).remove("timestamp");
                    }
                    if (actualJson instanceof ObjectNode) {
                        ((ObjectNode) actualJson).remove("timestamp");
                    }

                    isMatch = true;
                    for (Iterator<Map.Entry<String, JsonNode>> it = expectedJson.fields(); it.hasNext(); ) {
                        Map.Entry<String, JsonNode> entry = it.next();
                        if (!actualJson.has(entry.getKey()) || !actualJson.get(entry.getKey()).equals(entry.getValue())) {
                            isMatch = false;
                            break;
                        }
                    }
                }

                if (isMatch) {
                    result = "PASS";
                    passCount++;
                } else {
                    result = "FAIL";
                    failCount++;
                }

                // Ghi kết quả kiểm thử vào báo cáo HTML
                report.write("<tr>");
                report.write("<td>" + requestParamOrBody + "</td>");
                report.write("<td>" + expectedResponseBody + "</td>");
                report.write("<td>" + actualResponseBody + "</td>");
                report.write("<td>" + (result.equals("PASS") ? "<span style='color:green'>" : "<span style='color:red'>") + result + "</span></td>");
                report.write("</tr>");
            }

            // Kết thúc bảng và ghi summary
            report.write("</table>");
            report.write("<h2>Summary</h2>");
            report.write("<p>Total test cases: " + (passCount + failCount) + "</p>");
            report.write("<p style='color:green'>Pass: " + passCount + "</p>");
            report.write("<p style='color:red'>Fail: " + failCount + "</p>");

            // Kết thúc báo cáo HTML
            report.write("</body></html>");
            System.out.println("HTML report generated successfully with " + passCount + " passes and " + failCount + " failures.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "http://localhost:8100/api/fare";
        String method = "GET";
        String testcaseFilePath = "EO.txt";
        String reportFilePath = "report.html";

        callApi(url, method, testcaseFilePath, reportFilePath);
    }
}

