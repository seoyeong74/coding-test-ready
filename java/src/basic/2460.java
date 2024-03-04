import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

class Main_2460{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inBusNum = 0;
        int maxBusNum = 0;
        for(int i = 0; i< 10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int outNum = Integer.parseInt(st.nextToken()), inNum = Integer.parseInt(st.nextToken());

            inBusNum = inBusNum - outNum + inNum;
            maxBusNum = Math.max(inBusNum, maxBusNum);
        }

        bw.write(maxBusNum + "");

        br.close();
        bw.flush();
        bw.close();
    }
}