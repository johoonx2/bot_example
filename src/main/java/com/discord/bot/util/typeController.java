package com.discord.bot.util;

public class typeController {

//    아르  (고요한숲) : 00:00 (4시간)
//    자크  (로서스섬) : 00:00 (3시간)
//    카파 (마법사연구실) : 04:10 (2시간)
//    드레이크 (동쪽) : 00:00 (3시간)
//    드레이크 (중앙) : 02:42 (3시간)
//    드레이크 (서쪽) : 00:00 (2시간)
//    드레이크 (북쪽) : 00:00 (2시간)

    // for Lineage
    public final static int TIME_1_HOUR = 3600;
    public final static int TIME_30_MIN = 1800;
    public final static int TIME_3_MIN = 180;
    public final static int TIME_1_MIN = 60;

    public final static String LINEAGE_HELPER= "도움말";
    public final static String LINEAGE_SADARI_DEFAULT = "사다리";
    public final static String LINEAGE_SADARI_MODE2 = "사다리2";

    public final static String LINEAGE_BOSS_LIST = "보스리스트";         // 북드

    public final static String LINEAGE_BOSS_NORTH_DRAKE = "북드";         // 북드
    public final static String LINEAGE_BOSS_MIDDLE_DRAKE= "중드";         // 중드
    public final static String LINEAGE_BOSS_EAST_DRAKE = "동드";          // 동드
    public final static String LINEAGE_BOSS_WEST_DRAKE = "서드";          // 서드
    public final static String LINEAGE_BOSS_CASPA = "카스파";             // 카스파
    public final static String LINEAGE_BOSS_GIANTCROCODILE = "자크";      // 자크
    public final static String LINEAGE_BOSS_ARPIER = "아르";              // 아르피어

    public final static int LINEAGE_BOSS_NORTH_DRAKE_ID = 0;        // 북드
    public final static int LINEAGE_BOSS_MIDDLE_DRAKE_ID= 1;        // 중드
    public final static int LINEAGE_BOSS_EAST_DRAKE_ID = 2;         // 동드
    public final static int LINEAGE_BOSS_WEST_DRAKE_ID = 3;         // 서드
    public final static int LINEAGE_BOSS_CASPA_ID = 4;              // 카스파
    public final static int LINEAGE_BOSS_GIANTCROCODILE_ID = 5;     // 자크
    public final static int LINEAGE_BOSS_ARPIER_ID = 6;             // 아르피어


    public final static int LINEAGE_BOSS_NORTH_DRAKE_TIME = TIME_1_HOUR * 2;        // 북드 2H
    public final static int LINEAGE_BOSS_MIDDLE_DRAKE_TIME = TIME_1_HOUR * 3;       // 중드 3H
    public final static int LINEAGE_BOSS_EAST_DRAKE_TIME = TIME_1_HOUR * 3;         // 동드 3H
    public final static int LINEAGE_BOSS_WEST_DRAKE_TIME = TIME_1_HOUR * 2;         // 서드 2H
    public final static int LINEAGE_BOSS_CASPA_TIME = TIME_1_HOUR * 2;              // 카스파 2H
    public final static int LINEAGE_BOSS_GIANTCROCODILE_TIME = TIME_1_HOUR * 3;     // 자크 3H
    public final static int LINEAGE_BOSS_ARPIER_TIME = TIME_1_HOUR * 4;             // 아르피어 4H


    public final static String LINEAGE_HELPER_COMMAND_LIST = "보스 컷, 보스 삭, 보스 셋";
    public final static String LINEAGE_HELPER_BOSS_CUT = "컷시간 기록 : [보스명] 컷 [조정시간 -10] \n*조정시간이 없을경우 생략. -10일 경우 10분 전으로 설정 ";
    public final static String LINEAGE_HELPER_SADARI = "";
    public final static String LINEAGE_HELPER_BOSS_SUPPORT = "드레이크(북드,중드,동드,서드), 카스파, 자이언트 크로커다일(자크), 아르피어(아르)";
}
