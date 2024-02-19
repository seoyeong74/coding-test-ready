#include <stdio.h>
#include <iostream>
#include <vector>
#include <queue>
#include <list>
#include <utility>

using namespace std;

int N = 0, Q = 0;
int Max_Value = 1000000000;
vector<vector<pair<int, int>>> graph;
vector<int> min_usado;
vector<bool> visited;
queue<int> q;

int bfs(int start, int k) {
    q.push(start);

    int count = 0;

    while (!q.empty()){
        int from = q.front();
        visited[from] = true;
        q.pop();

        vector<pair<int, int> > edges = graph[from];

        for (vector<pair<int, int> >::iterator iter = edges.begin(); iter != edges.end(); iter++){
            int to = (*iter).first;
            int r = (*iter).second;

            if (visited[to] == false) {
                q.push(to);
                min_usado[to] = min(min_usado[from], r);

                if (k <= min_usado[to])
                    count++;   
            }
        }
    }
    return count;
}

int main() {
    cin >> N >> Q;
    graph.assign(N + 1, vector<pair<int, int>>(0, {0,0}));
    for (size_t i = 0; i < N - 1; i++){
        int p, q, r;
        cin >> p >> q >> r;

        graph[p].push_back(make_pair(q, r));
        graph[q].push_back(make_pair(p, r));
    }

    for (int i = 0; i < Q; i++){
        int k, v;
        cin >> k >> v;

        min_usado = vector<int>(N + 1, Max_Value);
        visited = vector<bool>(N + 1, false);
        cout << bfs(v, k) << endl;
     
    }

    return 0;
}
