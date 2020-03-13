//mamesukeの雛形
typedef struct Mamesuke{
int num;
int head;
int body;
int arm;
int leg;
} Mamesuke;

//ロボットに使用する関数の宣言
void custom(Mamesuke m[]);
void custom1(Mamesuke m[]);
void custom2(Mamesuke m[]);
void custom3(Mamesuke m[]);
void show(Mamesuke m[]);
void shows(Mamesuke *pC);

int num = 0; //ロボットのナンバーです。
int head = 0;
int body = 0;
int arm = 0;
int leg = 0;
valueM = 10; //ロボット作成時の初期値
int rn;//ロボット改造時のロボットナンバー
int cn;//ロボット改造時のカスタムナンバー