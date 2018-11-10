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

    public final static String LINEAGE_BOSS_PHOENIX = "피닉";             // 피닉스
    public final static String LINEAGE_BOSS_IFRIT = "이프";               // 이프리트
    public final static String LINEAGE_BOSS_MAYO = "마요";                // 큰 발의 마요
    public final static String LINEAGE_BOSS_GIANTDRAKE = "거드";          // 거대 드레이크
    public final static String LINEAGE_BOSS_ANCIENTGIANT = "에자";        // 에이션트 자이언트
    public final static String LINEAGE_BOSS_GIANTWORM = "웜";             // 자이언트 웜
    public final static String LINEAGE_BOSS_BANDIT = "산적";              // 산적 두목
    public final static String LINEAGE_BOSS_GREENCHASKI = "녹샤";         // 질풍의 샤스키
    public final static String LINEAGE_BOSS_REDCHASKI = "빨샤";           // 광풍의 샤스키
    public final static String LINEAGE_BOSS_RICHARD = "리칸트";           // 리칸트
    public final static String LINEAGE_BOSS_DOPPELGANGER = "도플";        // 도플갱어 보스
    public final static String LINEAGE_BOSS_SPIRID = "스피";              // 스피리드
    public final static String LINEAGE_BOSS_GASTROD = "가스트";           // 가스트 로드
    public final static String LINEAGE_BOSS_FAUST = "기감";               // 파우스트
    public final static String LINEAGE_BOSS_DEATHKNIGHT = "데스";         // 데스나이트
    public final static String LINEAGE_BOSS_KURTZ = "커츠";               // 커츠
    public final static String LINEAGE_BOSS_NECROMANCER = "네크";         // 네크로맨서
    public final static String LINEAGE_BOSS_BAPHOMET = "바포";            // 바포메트

    private static int index = 0;
    public final static int LINEAGE_BOSS_NORTH_DRAKE_ID  = index++;         // 북드
    public final static int LINEAGE_BOSS_MIDDLE_DRAKE_ID = index++;         // 중드
    public final static int LINEAGE_BOSS_EAST_DRAKE_ID = index++;           // 동드
    public final static int LINEAGE_BOSS_WEST_DRAKE_ID = index++;           // 서드
    public final static int LINEAGE_BOSS_CASPA_ID = index++;                // 카스파
    public final static int LINEAGE_BOSS_GIANTCROCODILE_ID = index++;       // 자크
    public final static int LINEAGE_BOSS_ARPIER_ID = index++;               // 아르피어

    public final static int LINEAGE_BOSS_PHOENIX_ID = index++;              // 피닉스
    public final static int LINEAGE_BOSS_IFRIT_ID = index++;                // 이프리트
    public final static int LINEAGE_BOSS_MAYO_ID = index++;                 // 큰 발의 마요
    public final static int LINEAGE_BOSS_GIANTDRAKE_ID = index++;           // 거대 드레이크
    public final static int LINEAGE_BOSS_ANCIENTGIANT_ID = index++;         // 에이션트 자이언트
    public final static int LINEAGE_BOSS_GIANTWORM_ID = index++;            // 자이언트 웜
    public final static int LINEAGE_BOSS_BANDIT_ID = index++;               // 산적 두목
    public final static int LINEAGE_BOSS_GREENCHASKI_ID = index++;          // 질풍의 샤스키
    public final static int LINEAGE_BOSS_REDCHASKI_ID = index++;            // 광풍의 샤스키
    public final static int LINEAGE_BOSS_RICHARD_ID = index++;              // 리칸트
    public final static int LINEAGE_BOSS_DOPPELGANGER_ID = index++;         // 도플갱어 보스
    public final static int LINEAGE_BOSS_SPIRID_ID = index++;               // 스피리드
    public final static int LINEAGE_BOSS_GASTROD_ID = index++;              // 가스트 로드
    public final static int LINEAGE_BOSS_FAUST_ID = index++;                // 파우스트
    public final static int LINEAGE_BOSS_DEATHKNIGHT_ID = index++;          // 데스나이트
    public final static int LINEAGE_BOSS_KURTZ_ID = index++;                // 커츠
    public final static int LINEAGE_BOSS_NECROMANCER_ID = index++;          // 네크로맨서
    public final static int LINEAGE_BOSS_BAPHOMET_ID = index++;             // 바포메트


    public final static int LINEAGE_BOSS_NORTH_DRAKE_TIME = TIME_1_HOUR * 2;        // 북드 2H
    public final static int LINEAGE_BOSS_MIDDLE_DRAKE_TIME = TIME_1_HOUR * 3;       // 중드 3H
    public final static int LINEAGE_BOSS_EAST_DRAKE_TIME = TIME_1_HOUR * 3;         // 동드 3H
    public final static int LINEAGE_BOSS_WEST_DRAKE_TIME = TIME_1_HOUR * 2;         // 서드 2H
    public final static int LINEAGE_BOSS_CASPA_TIME = TIME_1_HOUR * 2;              // 카스파 2H
    public final static int LINEAGE_BOSS_GIANTCROCODILE_TIME = TIME_1_HOUR * 3;     // 자크 3H
    public final static int LINEAGE_BOSS_ARPIER_TIME = TIME_1_HOUR * 4;             // 아르피어 4H

    public final static int LINEAGE_BOSS_PHOENIX_TIME = TIME_1_HOUR * 7;            // 피닉스
    public final static int LINEAGE_BOSS_IFRIT_TIME = TIME_1_HOUR * 2;              // 이프리트
    public final static int LINEAGE_BOSS_MAYO_TIME = TIME_1_HOUR * 3;               // 큰 발의 마요
    public final static int LINEAGE_BOSS_GIANTDRAKE_TIME = TIME_1_HOUR * 3;         // 거대 드레이크
    public final static int LINEAGE_BOSS_ANCIENTGIANT_TIME = TIME_1_HOUR * 5;       // 에이션트 자이언트
    public final static int LINEAGE_BOSS_GIANTWORM_TIME = TIME_1_HOUR * 2;          // 자이언트 웜
    public final static int LINEAGE_BOSS_BANDIT_TIME = TIME_1_HOUR * 3;             // 산적 두목
    public final static int LINEAGE_BOSS_GREENCHASKI_TIME = TIME_1_HOUR * 2;        // 질풍의 샤스키
    public final static int LINEAGE_BOSS_REDCHASKI_TIME = TIME_1_HOUR * 2;          // 광풍의 샤스키
    public final static int LINEAGE_BOSS_RICHARD_TIME = TIME_1_HOUR * 8;            // 리칸트
    public final static int LINEAGE_BOSS_DOPPELGANGER_TIME = TIME_1_HOUR * 4;       // 도플갱어 보스
    public final static int LINEAGE_BOSS_SPIRID_TIME = TIME_1_HOUR * 3;             // 스피리드
    public final static int LINEAGE_BOSS_GASTROD_TIME = TIME_1_HOUR * 3;            // 가스트 로드
    public final static int LINEAGE_BOSS_FAUST_TIME = TIME_1_HOUR;                  // 파우스트
    public final static int LINEAGE_BOSS_DEATHKNIGHT_TIME = TIME_1_HOUR * 7;        // 데스나이트
    public final static int LINEAGE_BOSS_KURTZ_TIME = TIME_1_HOUR * 10;             // 커츠
    public final static int LINEAGE_BOSS_NECROMANCER_TIME = TIME_1_HOUR * 2;        // 네크로맨서
    public final static int LINEAGE_BOSS_BAPHOMET_TIME = TIME_1_HOUR;               // 바포메트

    public final static String LINEAGE_HELPER_BOSS
            = "@ 보스 시간 관리\n" +
            "# 등록 : [보스] 컷 [옵션/시간]\n" +
            "- 중드 컷 -10 : 10분전 기준으로 중드 컷 지정 (옵션 생략가능)\n" +
            "# 삭제 : [보스] 삭 \n" +
            "- 중드 삭\n" +
            "# 설정 : [보스] 셋 - 보스 컷 시간 지정시간으로 설정\n" +
            "- 중드 셋 10:10\n" +
            "#################################################\n";
    public final static String LINEAGE_HELPER_SADARI
            = "@ 사다리타기 게임\n" +
            "# 사다리타기 : 사다리 [인원] [대상1],[대상2],[대상3] \n" +
            "- 사다리 2 캐스팅,다마,해롱\n" +
            "- 주의: 대상사이에는 공백(스페이스)가 있으면 안됩니다." +
            "#################################################\n";
    public final static String LINEAGE_HELPER_BOSS_SUPPORT = "@ 보스 지원 리스트\n- 드레이크(북드,중드,동드,서드), 카스파, 자이언트 크로커다일(자크), 아르피어(아르), 피닉스(피닉)," +
            "이프리트(이프), 마요, 에이션트 자이언트(에자), 자이언트 웜(웜), 산적 두목(산적), 질풍의 샤스키(녹샤), 광풍의 샤스키(빨샤), 리칸트, 도플갱어 보스(도플), 스피리드(스피)," +
            "가스트 로드(가스트), 파우스트(기감), 데스나이트(데스), 커츠" +
            "#################################################\n";
}