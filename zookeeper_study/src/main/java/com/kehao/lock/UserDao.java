package com.kehao.lock;

public class UserDao {

    private int score = 0;

    /**
     * 模拟从数据库获取用户积分
     * @return
     */
    public int getScoreFromDb() {
        //模拟网络请求耗时1毫秒
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //查徐数据
        return score;
    }

    /**
     * 模拟更新数据库里的用户积分
     * @param score
     */
    public void updateScore(int score) {
        //模拟网络请求耗时1毫秒
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.score = score;
    }

}
