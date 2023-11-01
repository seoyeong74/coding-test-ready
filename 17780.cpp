#include <iostream>
#include <vector>
#include <list>

using namespace std;

class Chess {
public:
    int x;
    int y;

    int toMove;
    bool isFirst = true;

    Chess(int i_x,int i_y, int i_move) {
        x = i_x;
        y = i_y;
        toMove = i_move;
    }
};

class ChessBoard {
public:
    int color = -1;
    list<Chess*> chessList = {};
};

int main() {

    int N, K;
    cin >> N >> K;

    ChessBoard chessboard[N][N];
    list<Chess> chessList = {};

    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            int inputColor;
            cin >> inputColor;

            chessboard[i][j].color = inputColor;
        }
    }

    for (int i = 0; i < K; i++){
        int i_x, i_y, i_move;
        cin >> i_x >> i_y >> i_move;

        Chess newChess = Chess(i_x, i_y, i_move);

        chessList.push_back(newChess);
        list<Chess*> chessListInBoard = chessboard[newChess.x][newChess.y].chessList;

        if (!chessListInBoard.empty())
            newChess.isFirst = false;
        
        chessListInBoard.push_back(&newChess);
    }

    
    int count = 0;

    while (count > 1000) {
        for(list<Chess>::iterator iter = chessList.begin(); iter != chessList.end(); iter++){
            Chess currentChess = *iter;

            if (!currentChess.isFirst)
                continue;

            int from_x = currentChess.x, from_y = currentChess.y;
            int to_x, to_y;

            switch (currentChess.toMove){
            case 1:
                to_x = from_x + 1;
                break;
            case 2:
                to_x = from_x - 1;
                break;
            case 3:
                to_y = from_y - 1;
                break;
            case 4:
                to_y = from_y - 1;
                break;
            default:
                break;
            }

            if (to_x < 0 || to_x >= N || to_y < 0 || to_y >= N) {
                chessList.erase(iter);

                
                continue;
            }
            
        }
    }
    
    return 0;
}