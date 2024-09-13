import java.util.*;
import java.io.*;

public class Main__ {
    public void main(String[] args) {

    }

    void bfs(int curr, boolean[] visited){

        if(curr == 2){
            for(boolean i: visited)
            {    System.out.print(i + " ");}
            System.out.println();
        }

        for(int i = curr; i< 2; i++){
            visited[i] = true;
            bfs(i + 1)
        }

    }
}
