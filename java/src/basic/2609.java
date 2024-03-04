// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.util.StringTokenizer;

// public class Main{
//     public static void main(String[] args) throws Exception{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//         StringTokenizer st = new StringTokenizer(br.readLine(), " ");

//         int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
//         if (a > b){
//             int t = b;
//             b = a;
//             a = t;
//         }

//         int biggerNum = Math.max(a, b);

//         int min = 1, max = 1;

//         for(int i = 2; i <= biggerNum; i++){
//             while (true){
//                 int extra_a_int = (a % i);
//                 int extra_b_int = (b % i);

//                 if (a > 1){
//                     if (extra_a_int == 0 && extra_b_int == 0) {
//                         min *= i;
//                         max *= i;

//                         a /= i;
//                         b /= i;
//                     }
//                     else if(extra_a_int != 0 && extra_b_int != 0)
//                         break;
//                     else{
//                         max *= i;

//                         if (extra_a_int == 0) a /= i;
//                         else b /= i;
//                     }                    
//                 }
//                 else {
//                     if (extra_b_int == 0) max *= i;
//                     else break;

//                     b /= i;
//                 }

//             }

//             if (a < 2 && b < 2) break;
//         }

//         bw.write(min + "\n" + max);

//         bw.flush();
//         bw.close();
//         br.close();
//     }
// }

//수학을 이용하자..
import java.util.*;
import java.io.*;
class Main_2609 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int K = A;
        int N = B;
        int tmp = 0;
        while (K % N != 0) {
            tmp = N;
            N = K % N;
            K = tmp;
        }

        System.out.println(N);
        System.out.println(A * B / N);
    }
}