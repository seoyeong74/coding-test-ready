import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class Main_2501 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int i = 0, result = 0;

        for (int curr = 1;curr<=10000; curr++){
            if ((N % curr) == 0)
                i++;
            if (i == k){
                result = curr;
                break;
            }
        }

        bw.write(result + "");
        
        br.close();
        bw.flush();
        bw.close();
    }
}

