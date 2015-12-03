package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.Status;
import twitter4j.User;

/**
 * OnFavoriteListener
 */
public interface OnFavoriteListener {
    /**
     * @param source          お気に入りを実行したユーザー
     * @param target          お気に入りを実行されたユーザー
     * @param favoritedStatus お気に入りされたステータス
     */
    void onFavorite(User source, User target, Status favoritedStatus);
}
