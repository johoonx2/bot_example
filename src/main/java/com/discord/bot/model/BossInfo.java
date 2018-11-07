package com.discord.bot.model;

import java.util.Timer;

public class BossInfo {

    // 보스 ID
    private int bossID = 0;

    // 보스명
    private String bossName = "";

    // 타이머
    private Timer bossTimer;

    // 젠간격
    private int bossInterval = 0;

    // 남은 시간
    private int bossRestTime = 0;

    // 지난 시간
    private int bossTimeCounter = 0;

    // 활성 여부
    private boolean isActivated = false;

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public int getBossID() {
        return bossID;
    }

    public void setBossID(int bossID) {
        this.bossID = bossID;
    }

    public int getBossInterval() {
        return bossInterval;
    }

    public void setBossInterval(int bossInterval) {
        this.bossInterval = bossInterval;
    }

    public int getBossRestTime() {
        return bossRestTime;
    }

    public void setBossRestTime(int bossRestTime) {
        this.bossRestTime = bossRestTime;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Timer getBossTimer() {
        return bossTimer;
    }

    public void setBossTimer(Timer bossTimer) {
        this.bossTimer = bossTimer;
    }

    public int getBossTimeCounter() {
        return bossTimeCounter;
    }

    public void setBossTimeCounter(int bossTimeCounter) {
        this.bossTimeCounter = bossTimeCounter;
    }

    public BossInfo(String bossName, int bossID, int bossInterval, int bossRestTime, boolean activated){
        this.bossName = bossName;
        this.bossID = bossID;
        this.bossInterval = bossInterval;
        this.bossRestTime = bossRestTime;
        this.isActivated = activated;
    }

    public void increaseSec(){
        this.bossTimeCounter++; // 지난 시간 ++
        this.bossRestTime--;    // 남은 시간 --
    }
}
