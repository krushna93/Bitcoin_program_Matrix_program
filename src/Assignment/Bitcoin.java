package Bitcoin_program;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Bitcoin {
    public static void main(String[] args) {
        try {
           
            URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

          
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();

            JSONObject json = new JSONObject(response.toString());

            JSONObject bpi = json.getJSONObject("bpi");
            String rate = bpi.getJSONObject("USD").getString("rate");

           
            String rateInWords = convertToWords(rate);

           
            System.out.println(rateInWords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String convertToWords(String number) {
        String[] units = {
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };
        String[] tens = {
                "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        number = number.replace(",", "").split("\\.")[0].replaceAll("\\s+", ""); // Remove commas, decimal part, and extra spaces
        int num = Integer.parseInt(number);

        if (num < 20) {
            return units[num];
        } else if (num < 100) {
            int tenDigit = num / 10;
            int unitDigit = num % 10;
            return tens[tenDigit] + " " + units[unitDigit];
        } else if (num < 1000) {
            return units[num / 100] + " Hundred " + convertToWords(String.valueOf(num % 100));
        } else if (num < 100000) {
            return convertToWords(String.valueOf(num / 1000)) + " Thousand " + convertToWords(String.valueOf(num % 1000));
        } else if (num < 10000000) {
            return convertToWords(String.valueOf(num / 100000)) + " Lakh " + convertToWords(String.valueOf(num % 100000));
        } else {
            return convertToWords(String.valueOf(num / 10000000)) + " Crore " + convertToWords(String.valueOf(num % 10000000));
        }
    }
}
