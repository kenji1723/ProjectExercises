package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.User;

/**
 * OnUnBlockListener
 */
public interface OnUnBlockListener {
    /**
     * @param source        ブロック解除を実行したユーザー
     * @param unblockedUser ブロック解除を実行されたユーザー
     */
    void onUnBlock(User source, User unblockedUser);
}
