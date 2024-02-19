#include <iostream>
#include <vector>
#include <list>

using namespace std;

int N, K;

class Chess {
public:
    int x;
    int y;

    int toMove;
    bool isFirst = true;
    bool isOutRange = false;

    Chess(int i_x,int i_y, int i_move) {
        x = i_x;
        y = i_y;
        toMove = i_move;
    }
};

class ChessBoard {
public:
    int color = -1;
    list<Chess*> chessList;
};

ChessBoard chessboard[9][9];
list<Chess> chessList;

bool doWhiteBoard(
    list<Chess*> fromChessList,
    list<Chess*> toChessList
) {
    for (auto const &i: fromChessList){
        toChessList.push_back(i);
    }
    fromChessList.clear();

    return toChessList.size() > 4;
}

bool doRedBoard(
    list<Chess*> fromChessList,
    list<Chess*> toChessList
) {
    fromChessList.reverse();
    for (auto const &i: fromChessList){
        toChessList.push_back(i);
    }
    fromChessList.clear();

    return toChessList.size() > 4;
}

bool checkOutRange(
    int x, int y
) {
    return (x < 0 || x >= N || y < 0 || y >= N);
}

bool doBlueBoard(
    Chess currentChess
) {
    int changedDirection = (currentChess.toMove + 2) % 4;

    int from_x = currentChess.x, from_y = currentChess.y;
    int to_x, to_y;

    switch (changedDirection){
    case 0:
        to_x = from_x + 1;
        break;
    case 1:
        to_x = from_x - 1;
        break;
    case 2:
        to_y = from_y - 1;
        break;
    case 3:
        to_y = from_y - 1;
        break;
    default:
        break;
    }

    ChessBoard fromChessBoardInfo = chessboard[from_x][from_y];
    if (checkOutRange(to_x, to_y)) {
        for (auto const &i: fromChessBoardInfo.chessList)
            i->isOutRange = true;
        
        return false;
    }

    ChessBoard toChessBoardInfo = chessboard[to_x][to_y];
    switch (toChessBoardInfo.color){
    case 0:
        doWhiteBoard(fromChessBoardInfo.chessList, toChessBoardInfo.chessList);
        break;
    case 1:
        doRedBoard(fromChessBoardInfo.chessList, toChessBoardInfo.chessList);
        break;
    case 2:
        break;
    default:
        break;
    }
    
    return toChessBoardInfo.chessList.size() > 4;
}

int main() {
    cin >> N >> K;

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

        Chess newChess = Chess(i_x - 1, i_y - 1, i_move - 1);

        chessList.push_back(newChess);
        list<Chess*> chessListInBoard = chessboard[newChess.x][newChess.y].chessList;

        if (!chessListInBoard.empty())
            newChess.isFirst = false;
        
        chessListInBoard.push_back(&newChess);
    }

    
    int count = 0;

    while (count <= 1000) {
        bool isOverFour = false;
        for(list<Chess>::iterator iter = chessList.begin(); iter != chessList.end(); iter++){
            Chess currentChess = *iter;

            if(currentChess.isOutRange)
                continue;
            if (!currentChess.isFirst)
                continue;

            int from_x = currentChess.x, from_y = currentChess.y;
            int to_x, to_y;

            switch (currentChess.toMove){
            case 0:
                to_x = from_x + 1;
                break;
            case 1:
                to_x = from_x - 1;
                break;
            case 2:
                to_y = from_y - 1;
                break;
            case 3:
                to_y = from_y - 1;
                break;
            default:
                break;
            }
            ChessBoard fromChessBoardInfo = chessboard[from_x][from_y];

            if (checkOutRange(to_x, to_y)) {
                for (auto const &i: fromChessBoardInfo.chessList)
                    i->isOutRange = true;
                
                continue;
            }

            ChessBoard toChessBoardInfo = chessboard[to_x][to_y];
            switch (toChessBoardInfo.color){
            case 0:
                isOverFour = doWhiteBoard(fromChessBoardInfo.chessList, toChessBoardInfo.chessList);
                break;
            case 1:
                isOverFour = doRedBoard(fromChessBoardInfo.chessList, toChessBoardInfo.chessList);
                break;
            case 2:
                isOverFour = doBlueBoard(currentChess);
                break;
            default:
                break;
            }
        }

        count++;

        if (chessList.size() < 4){
            count = -1;
            break;
        }

        if (isOverFour)
            break;
    }

    cout << count << endl;
    
    return 0;
}