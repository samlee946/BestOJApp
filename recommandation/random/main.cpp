#include <cstdio>
#include <cstring>
#include <cmath>
#include <iostream>
#include <algorithm>
#include <queue>
#include <map>
#include <time.h>
#include <string>
#include <windows.h>
typedef long long ll;
using namespace std;

bool vis[1234];
ll a[1234];

void dealrandom() {
    int T, cnt = 0, randomcnt = 0;
    memset(vis, 0, sizeof vis);
    scanf("%d", &T);
    while(~scanf("%I64d", &a[cnt])) cnt++;
    while(randomcnt < T) {
        int t = rand() % cnt;
        if(!vis[t]) {
            vis[t] = 1;
            printf("%I64d\n", a[t]);
            randomcnt++;
        }
    }
}
int main(){
    #ifdef LOCAL
//    freopen("F:\\C++\\random\\in.txt", "r", stdin);
//    freopen("F:\\C++\\random\\out.txt", "w", stdout);
    #endif // LOCAL
    srand(time(NULL));
    dealrandom();
}
