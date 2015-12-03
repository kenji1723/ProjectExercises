package jp.ac.kcg.projectexercises.twitter.client.event;

import twitter4j.StatusDeletionNotice;

/**
 * OnDeletionNoticeListener
 */
public interface OnDeletionNoticeListener {
    /**
     * Statusの削除通知
     *
     * @param statusDeletionNotice 通知オブジェクト
     */
    void onDeletionNotice(StatusDeletionNotice statusDeletionNotice);
}
