import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class Main
{
    static int result = Integer.MIN_VALUE;;
    static boolean[] learnCharList = new boolean[26];
    static ArrayList<String> wordList = new ArrayList<>();

    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        if(K < 5){
            System.out.println("0");
            return;
        }
        else if(K == 26){
            System.out.println(N);
            return;
        }

        learnCharList[('a' - 'a')] = true;
        learnCharList[('n' - 'a')] = true;
        learnCharList[('t' - 'a')] = true;
        learnCharList[('i' - 'a')] = true;
        learnCharList[('c' - 'a')] = true;

        
        //HashSet<Character> toLearnChar = new HashSet<>();

        for(int i = 0; i< N; i++){
            String inputString = br.readLine().replaceAll("a|n|t|i|c", "");
            wordList.add(inputString);
        }

        bfs(K - 5, 0);

        br.close();
        System.out.println(result);
    }

    static void bfs(int ableCharNum, int lastLearnIdx){

        if (ableCharNum == 0){ //배울 알파벳 다 배움
            int readWordNum = 0;
            for(String word: wordList){
                boolean ableRead = true;
                for(char charc: word.toCharArray()){
                    ableRead = learnCharList[charc - 'a'];
                    if(!ableRead)
                        break;
                }

                if(ableRead) readWordNum++;
            }

            result = Math.max(result, readWordNum);

            return;
        }

        for(int i = lastLearnIdx; i < 26; i++){
            if(!learnCharList[i]){
                learnCharList[i] = true;
                bfs(ableCharNum - 1, i);
                learnCharList[i] = false;    
            }
            
        }
    }
}