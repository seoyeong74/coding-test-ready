import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

class class_10430 {
    public static void main(String[] arg) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken()), 
        B = Integer.parseInt(st.nextToken()), 
        C = Integer.parseInt(st.nextToken())
        ;
        
        bw.write(
            ((A+B)%C) + "\n"
            + (((A%C) + (B%C))%C) + "\n"
            + ((A*B)%C) + "\n"
            + (((A%C) * (B%C))%C) + "\n"
        );

        br.close();
        bw.flush();
        bw.close();
    }
    
}
