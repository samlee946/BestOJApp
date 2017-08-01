#include <cstdio>
#include <cstring>
#include <cmath>
#include <iostream>
#include <algorithm>
#include <queue>
#include <map>
#include <string>
#include <time.h>
typedef long long ll;
using namespace std;

int db[205][1005];
int pplcnt;

//bool isexam

int inp[1000];

void printTest() {
    printf("%d\n", pplcnt);
    for(int i = 1; i <= pplcnt; i++) {
        int cnt = db[i][0];// = (rand() % db[i][0]) + 1;
        for(int j = 0; j <= cnt; j++) {
            printf("%d ", db[i][j]);
        }
        puts("");
    }
}
void getArray() {
    char tmp[123];
    int t, cnt = 0;;
    pplcnt = 0;
    memset(db, 0, sizeof db);
    while(~scanf("%s", tmp)) {
        if(!sscanf(tmp, "%d", &t)) {
            db[pplcnt][0] = cnt;    //save cnt into the first element in array
            pplcnt++;
            cnt = 0;
        }
        else {
            db[pplcnt][++cnt] = t;
        }
    }
    db[pplcnt][0] = cnt;
    db[0][0] = pplcnt;
    printTest();
}

int main(){
    #ifdef LOCAL
//    freopen("F:\\C++\\transform\\in.txt", "r", stdin);
//    freopen("out.txt", "w", stdout);
//    freopen("F:\\C++\\recommandation\\in.txt", "w", stdout);
    #endif // LOCAL
    srand(time(NULL));
    getArray();
}
