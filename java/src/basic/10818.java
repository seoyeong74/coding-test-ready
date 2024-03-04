import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

class Main_10818 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int max = -1000001, min = 1000001;

        for(int i = 0; i < N; i++){
            int curr = Integer.parseInt(st.nextToken());
            if (curr > max) max = curr;
            if (curr < min) min = curr;
        }

        bw.write(min + " " + max);

        br.close();
        bw.flush();
        bw.close();
    }
}