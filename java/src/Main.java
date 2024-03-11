import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class Main
{
    static int N, K, result = 0;
    static int[] useToolList = new int[101];
    static ToolInfo[] toolInfoList = new ToolInfo[101];

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i< K; i++){
            toolInfoList[i] = new ToolInfo();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < K; i++){
            int tool = Integer.parseInt(st.nextToken()) - 1;
            useToolList[i] = tool;
            toolInfoList[tool].idxList.offer(i);
        }

        ArrayList<ToolInfo> pulgInToolList = new ArrayList<>();

        int remainPlugNum = N;
        for(int i = 0; i < K; i++){
            int toPlugTool = useToolList[i];
            ToolInfo toPlugToolInfo = toolInfoList[toPlugTool];

            if (toPlugToolInfo.isPlug){
                toPlugToolInfo.idxList.poll();
            }
            else if (remainPlugNum > 0){
                pulgInToolList.add(toPlugToolInfo);
                toPlugToolInfo.isPlug = true;
                toPlugToolInfo.idxList.poll();
                remainPlugNum--;
            }
            else {
                Collections.sort(
                    pulgInToolList,
                    new Comparator<ToolInfo>(){
                        @Override
                        public int compare(ToolInfo a, ToolInfo b){
                            int bIdx = Integer.MAX_VALUE, aIdx = Integer.MAX_VALUE;
                            if (!a.idxList.isEmpty()) {
                                aIdx = a.idxList.peek();
                            }
                            if (!b.idxList.isEmpty()) {
                                bIdx = b.idxList.peek();
                            }
                            return Integer.compare(bIdx, aIdx);
                        }
                    }
                ); //다음으로 나오는 idx가 큰 순으로 정렬

                ToolInfo outToolInfo = pulgInToolList.get(0);
                outToolInfo.isPlug = false;

                toPlugToolInfo.isPlug = true;
                toPlugToolInfo.idxList.poll();

                pulgInToolList.remove(outToolInfo);
                pulgInToolList.add(toPlugToolInfo);
                result++;
            }
        }

        System.out.println(result);

        br.close();
    }

    static class ToolInfo{
        boolean isPlug = false;
        Queue<Integer> idxList = new LinkedList<>();
    }
}