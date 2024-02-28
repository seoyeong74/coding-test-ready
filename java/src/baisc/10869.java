import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class Main_10869 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

        bw.write(
            (a + b) + "\n" 
            + (a - b) + "\n" 
            + (a * b) + "\n" 
            + (a / b) + "\n" 
            + (a % b) + "\n"
        );

        br.close();
        bw.flush();
        bw.close();
    }
}
