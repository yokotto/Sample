#include <stdio.h>
#include <stdlib.h>
#include "myfunc.h"
#define CUS 50 //カスタムでの能力値の上昇量を調整します。
#define TOTAL 3 //ロボットの制作数の上限を設定します。
#define TOTALC 3 //カスタムの種類の総数を設定します。
#define READ 30 //テキストの1行に読みとれる文字の上限を設定します。

int main(void)
{

Mamesuke mamesukes[TOTAL];
int i; //各種構文にて使用　構文内で被らないよう注意。
FILE *fp; //テキストファイル操作用の変数
char txt[READ];//文字列入力用の変数です。

//以下　readme.txt　ファイルを読み込みます。
printf("説明を確認しますか？確認する場合は1を入力してください。確認しない場合は2を入力してください。");
scanf("%d",&i);
if(i == 1){
printf("説明を表示します。\n");

fp = fopen("readme.txt", "r"); //左が読込ファイル　右がオープンモード

if(fp == NULL){
printf("ファイルをオープンできませんでした。\n");
return 1;
} else {
printf("\n");
}

while((i = fgetc(fp)) != EOF) {
putchar(i);
}

fclose(fp); //使い終わったら必ず閉める！

} else {
printf("説明を飛ばします。\n");
}

printf("プログラムを開始してよろしいですか？よろしければ1を入力してください。");
scanf("%d",&i);
if(i == 1){
printf("プログラムを開始します。\n");
} else {
printf("プログラムを終了します。\n");
exit(0);
}

for(int t=0; t<TOTAL; t++){
mamesukes[t].num = t;
mamesukes[t].head = valueM; mamesukes[t].body = valueM; 
mamesukes[t].arm = valueM; mamesukes[t].leg = valueM;
shows(&mamesukes[t]);
}

custom(mamesukes);

show(mamesukes);

return 0;
}

//ロボット改造のための関数(必要に応じてファイル分割後リンク)
//今回はWindows PowerShellでの開発につき分割しませんでした。
void custom(Mamesuke m[]){
printf("\n施す改造の番号を1～%dから選んでください。\n",TOTALC);
scanf("%d",&cn);
switch(cn){
case 1:
custom1(m);
break;
case 2:
custom2(m);
break;
case 3:
custom3(m);
break;
default:
printf("正しい改造番号1～%dを選んでください。改造をスキップします。\n\n",TOTALC);
break;
	}
}
void custom1(Mamesuke m[]){
printf("改造を施すロボットの番号を0～%dから選んでください。\n",TOTAL-1);
scanf("%d",&rn);
printf("mamesuke[%d]に基礎改造を施しました。\n\n",m[rn].num);
m[rn].head += CUS;
m[rn].body += CUS;
m[rn].arm += CUS;
m[rn].leg += CUS;
}

void custom2(Mamesuke m[]){
printf("改造を施すロボットの番号を0～%dから選んでください。\n",TOTAL-1);
scanf("%d",&rn);
printf("mamesuke[%d]にまめでっぽうを装着しました。\n\n",m[rn].num);
m[rn].head += (CUS-CUS);
m[rn].body += (CUS-CUS);
m[rn].arm += (CUS+200);
m[rn].leg += (CUS-CUS);
}

void custom3(Mamesuke m[]){
printf("改造を施すロボットの番号を0～%dから選んでください。\n",TOTAL-1);
scanf("%d",&rn);
printf("mamesuke[%d]に基礎改造3を施しました。\n\n",m[rn].num);
m[rn].head += (CUS+100);
m[rn].body += (CUS+100);
m[rn].arm += (CUS+100);
m[rn].leg += (CUS+100);
}

//ロボットのステータス確認のための関数
void show(Mamesuke m[]){
printf("能力を確認したいロボットの番号を0～%dから選んでください。\n",TOTAL-1);
scanf("%d",&rn);
if(rn <= TOTAL){
printf("\n現在のmamesuke[%d]の能力は\n頭:%d 体:%d 腕:%d 足:%dです。サイズは%dです。\n",
m[rn].num, m[rn].head, m[rn].body, m[rn].arm, m[rn].leg,sizeof(m[rn]));
} else {
printf("正しいロボット番号を入力してください。確認をスキップします。\n");
	}
}

//ロボットの初期値ステータス確認のための関数
void shows(Mamesuke *pC){
printf("\n新しいロボットを製造しました。\nmamesuke[%d]の初期能力は\n頭:%d 体:%d 腕:%d 足:%dです。サイズは%dです。\n",
pC->num, pC->head, pC->body, pC->arm, pC->leg,sizeof(*pC));
}