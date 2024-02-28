import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main_10699 {
    public static void main(String args[]) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd");

        bw.write(now.format(df) + "");

        bw.flush();
        bw.close();
    }
    
}
