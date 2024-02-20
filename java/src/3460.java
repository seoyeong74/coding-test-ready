import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main_3460 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        
        for(int test_i = 0; test_i < T; test_i++){
            int N = Integer.parseInt(br.readLine());

            int curr = N;
            int i = 0;
            while(curr > 0){
                int new_num = curr / 2;
                int extra = curr % 2;

                if (extra == 1)
                    bw.write((i) + " ");

                curr = new_num;
                i++;
            }

            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}