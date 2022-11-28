package com.example.userdetails.helper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CsvReader {
    static ArrayList<String> readAsStrings(String url) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        //INPUTSTREAM READER CONVERTS BYTES TO CHARACTERSS
        //BUFFER INPUT STREAM READER READS
        // FROM A CHARACTER INPUT STREAM
        //BUFFERS CHARACTERS FOR EFFICIENCY
        //USE WITH FILE READERS AND INPUT STREAM READER
        //READS A CHARACTER INTO A BUFFER FOR MORE EXCELLENT OPERATION
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(url)))) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                data.add(nextLine);
                nextLine = reader.readLine();
            }
        }
        return data;
    }



    public String request(String endpoint) throws Exception  {

        //if we are reading the json it means it reads one after the other
        //to make all of them to be as one string we use string builder
        StringBuilder sb = new StringBuilder();

        URL url = new URL(endpoint);

        // open a connection to this URL
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // read in the bytes
            //input stream has methods that read bytes and next set of bytes
            InputStream inputStream = urlConnection.getInputStream();
            //Buffered Input stream extends input stream recieves input stream as a constructer( also argument)
            //reasds into an internal array
            //refill the array as bytes are read
            //mark and resets, for performance reason
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // read them as characters.
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // read one line at a time.
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                // add this to our output
                sb.append(inputLine);
                // reading the next line
                inputLine = bufferedReader.readLine();
            }
        } finally {
            urlConnection.disconnect();
        }
        return sb.toString();

    }


    static ArrayList extractFromCommas(String dataLine) {
        //Gives back the data that is found between commas in a String
        ArrayList<String> data = new ArrayList<>();
        String theString = "";
        for (int i = 0; i < dataLine.length(); i++) { //go down the whole string
            if (dataLine.charAt(i) == ',') {
                if (i != 0) {
                    data.add(theString); //this means that the next comma has been reached
                    theString = ""; //reset theString Variable
                }
            } else {
                theString = theString + dataLine.charAt(i); //otherwise, just keep piling the chars onto the cumulative string
            }
        }
        if (!theString.equalsIgnoreCase("")) //only if the last position is not occupied with nothing then add the end on
        {
            data.add(theString);
        }
        return data;
    }
}

