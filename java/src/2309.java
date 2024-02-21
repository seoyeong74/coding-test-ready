import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Collections;

public class Main_2309{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] heightList = new int[9];
        int heightSum = 0;

        for(int i = 0; i < 9; i++){
            int height = Integer.parseInt(br.readLine());
            heightList[i] = height;
            heightSum += height;
        }

        int diff = heightSum - 100;
        ArrayList<Integer> resultHeight = new ArrayList<Integer>();

        for(int i = 0; i< 9; i++){
            for(int j= 0; j< 9; j++){
                if (i == j) continue;

                if (diff == (heightList[i] + heightList[j])){

                    for(int z = 0; z< 9; z++){
                        if (z == i || z== j)
                            continue;
                            resultHeight.add(heightList[z]);
                    }
                    break;
                }
            }

            if (resultHeight.size() > 0)
                break;
        }

        Collections.sort(resultHeight);

        for(int i: resultHeight){
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}