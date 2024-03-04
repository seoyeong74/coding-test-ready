import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.*;

class Main_2693{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testNum = Integer.parseInt(br.readLine());

        for(int i = 0; i< testNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            ArrayList<Integer> numList = new ArrayList<>(10);

            for(int j = 0; j < 10; j++){
                numList.add(Integer.parseInt(st.nextToken()));
            }

            numList.sort(Comparator.naturalOrder());

            bw.write(numList.get(7) + "\n");
        }

        br.close();

        bw.flush();
        bw.close();
    }
}