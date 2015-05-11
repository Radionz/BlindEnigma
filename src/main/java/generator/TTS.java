package main.java.generator;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TTS {
    private static boolean ended = true;

    public static boolean ttsFromString(String str, String nameOutputFile,
                                        Language langage) {
        ended = false;
        try {
            URL url = new URL("http://translate.google.com/translate_tts?tl="
                    + langage.getLanguage() + "&q=" + URLEncoder.encode(str, "UTF-8"));
            //URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20")
            //System.out.println(url);
            HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();
            urlConn.addRequestProperty("User-Agent", "Mozilla/4.76");
            InputStream audioSrc = urlConn.getInputStream();
            DataInputStream read = new DataInputStream(audioSrc);
            OutputStream outstream = new FileOutputStream(new File(nameOutputFile));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = read.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
            ended = true;
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public enum Language {
        // Objets directement construits
        FRENCH("fr"), ENGLISH("en"), SPANISH("es");

        private String language;

        // Constructeur
        Language(String language) {
            this.language = language;
        }

        public String getLanguage() {
            return language;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }
}
