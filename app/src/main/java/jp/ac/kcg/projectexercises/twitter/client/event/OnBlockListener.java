package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.User;

/**
 * OnBlockListener
 */
public interface OnBlockListener {
    /**
     * @param source      ブロックを実行したユーザー
     * @param blockedUser ブロックを実行されたユーザー
     */
    void onBlock(User source, User blockedUser);
}
