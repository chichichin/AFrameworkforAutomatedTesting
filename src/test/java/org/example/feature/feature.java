package org.example.feature;

import org.example.base.*;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;


public class feature {
    genTestcase genTestcase =  new genTestcase();
    genTestcase_final genTestcase_final =  new genTestcase_final();
    ApiTestWithGenDataUlti testAPI = new ApiTestWithGenDataUlti();
    @Test
    public void fare() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");


        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();

        String xmlFilePath = "fare_requirement.xml";
        String url = "/api/fare";
        String method = "GET";
        String testcaseFilePath = "fare_testcases.txt";
        String reportFilePath = "fareCalculate_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);
        testAPI.callApi(url, method, testcaseFilePath, reportFilePath);

        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();

      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;

        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void grade() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {
        // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "grade_requirement.xml";
        String url = "/api/grade";
        String method = "GET";
        String testcaseFilePath = "grade_testcases.txt";
        String reportFilePath = "gradeCalculate_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void customerDiscount() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "customerDiscount_requirement.xml";
        String url = "/api/CustomerDiscount";
        String method = "GET";
        String testcaseFilePath = "customerDiscount_testcases.txt";
        String reportFilePath = "customerDiscount_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void determineExamResult() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "determineExamResult_requirement.xml";
        String url = "/api/determineExamResult";
        String method = "GET";
        String testcaseFilePath = "determineExamResult_testcases.txt";
        String reportFilePath = "determineExamResult_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void FinalPrice() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "FinalPrice_requirement.xml";
        String url = "/api/FinalPriceCalculate";
        String method = "GET";
        String testcaseFilePath = "FinalPrice_testcases7.txt";
        String reportFilePath = "FinalPrice_report7.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void lateBonus() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "lateBonus_requirement.xml";
        String url = "/api/lateBonusCalcu";
        String method = "GET";
        String testcaseFilePath = "lateBonus_testcases.txt";
        String reportFilePath = "lateBonus_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void MathEnglishGrade() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "MathEnglishGrade_requirement.xml";
        String url = "/api/MathEnglishGradeCalculator";
        String method = "GET";
        String testcaseFilePath = "MathEnglishGrade_testcases.txt";
        String reportFilePath = "MathEnglishGrade_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void shippingFare() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "ShippingFare_requirement.xml";
        String url = "/api/shippingFare";
        String method = "GET";
        String testcaseFilePath = "ShippingFare_testcases.txt";
        String reportFilePath = "ShippingFare_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void tax() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "tax_requirement.xml";
        String url = "/api/calculateTax";
        String method = "GET";
        String testcaseFilePath = "tax_testcases.txt";
        String reportFilePath = "tax_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    @Test
    public void shippingCost() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        // Lấy thông tin bộ nhớ trước khi chạy
       // Lấy thông tin bộ nhớ trước khi chạy
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory before execution: " + formatMemory(memoryBefore) + " MB");

        // Ghi nhận thời gian bắt đầu
        long startTime = System.nanoTime();
        String xmlFilePath = "shippingCost_requirement.xml";
        String url = "/api/getShippingCost";
        String method = "GET";
        String testcaseFilePath = "shippingCost_testcases.txt";
        String reportFilePath = "shippingCost_report.html";

        genTestcase_final.createDataset(xmlFilePath, url, method, testcaseFilePath);

        testAPI.callApi(url, method, testcaseFilePath,reportFilePath);
        // Ghi nhận thời gian kết thúc
        long endTime = System.nanoTime();
      // Lấy thông tin bộ nhớ sau khi chạy
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory after execution: " + formatMemory(memoryAfter) + " MB");

        // Bộ nhớ được sử dụng trong quá trình chạy
        System.out.println("Memory used during execution: " + formatMemory(memoryAfter - memoryBefore) + " MB");;
        // Tính thời gian thực thi
        long elapsedTime = endTime - startTime; // thời gian tính bằng nanosecond
        System.out.println("Execution time: " + elapsedTime / 1_000_000 + " ms"); // chuyển đổi sang millisecond
    }
    private static String formatMemory(long bytes) {
        double megabytes = bytes / 1024.0 / 1024.0;
        return String.format("%.2f", megabytes); // Định dạng 2 chữ số sau dấu phẩy
    }
}


