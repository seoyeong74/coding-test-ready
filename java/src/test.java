
class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int friendNum = friends.length;
        Map<Pair, Integer> giftMap = new Map<>();
        Map<String> friendGiftNumList = new Map<>();
        
        for (int i = 0; i< gifts.length(); i++){
            StringTokenizer st = new StringTokenizer(gifts[i]);
            string giver = st.nextToken(), getter = st.nextToken();
            Pair key = new Pair(giver, getter);
            
            //주고 받는 것 저장
            giftMap.put(key, giftMap.getOrDefault(key, 0) + 1);
            
            //주는 거 저장
            friendGiftNumList.put(giver, friendGiveNumList.getOrDefault(giver, 0) + 1);
            friendGiftNumList.put(getter, friendGiveNumList.getOrDefault(getter, 0) - 1);
        }
        
        Map<String, int> friendsGetNum = new Map<>();
        
        for (int i = 0; i < friendNum; i++){
            string A = friends[i];
            for(int j = 0; j < friendNum; j++){
                if (i == j)
                    continue;
                
                string B = friends[j];
                
                Pair AToBKey = new Pair(A, B);
                Pair BToAKey = new Pair(B, A);
                
                int AToBNum = giftMap.getOrDefault(AToBKey, 0);
                int BToANum = giftMap.getOrDefault(BToAKey, 0);
                
                if (AToBNum > BToANum){
                    friendsGetNum.put(A, friendsGetNum.getOrDefault(A, 0) + 1);
                }
                else if (BToANum > AToBNum){
                    friendsGetNum.put(B, friendsGetNum.getOrDefault(B, 0) + 1);
                }
                else{
                    int AGiftNum = friendGiveNumList.getOrDefault(A, 0);
                    int BGiftNum = friendGiveNumList.getOrDefault(B, 0);
                    if (BGiftNum > AGiftNum){
                        friendsGetNum.put(B, friendsGetNum.getOrDefault(B, 0) + 1);
                    } else if (BGiftNum < AGiftNum){
                        friendsGetNum.put(A, friendsGetNum.getOrDefault(A, 0) + 1);
                    }
                }
                
            }    
        }
        
        int answer = 0;
        
        for(String friend: friendsGetNum.keySet()){
            answer = Math.max(friendsGetNum.get(friend), answer);
        }
        
        return answer;
    }
    
    static class Pair{
        String giver;
        String taker;
    }
}

