import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main_10870{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        //solv 1
        // if (n < 2)
        //     bw.write(n + "");
        // else {
        //     int curr = 0;
        //     int i_2 = 0, i_1 = 1;

        //     for(int i = 2; i < n + 1; i++){
        //         curr = i_1 + i_2;

        //         i_2 = i_1;
        //         i_1 = curr;
        //     }

        //     bw.write(curr + "");
        // }

        //solv 2
        int[] buffer = new int[21];
        buffer[0] = 0;
        buffer[1] = 1;

        for(int i = 2; i < n + 1; i++){
            int prev = buffer[i-1];
            int prevPrev = buffer[i-2];

            buffer[i] = prev + prevPrev;
        }

        bw.write(buffer[n] + "");
        
        bw.flush();
        bw.close();
        br.close();
    }
}