package jp.ac.kcg.projectexercises.twitter.client;

import jp.ac.kcg.projectexercises.twitter.client.event.OnBlockListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnDeletionNoticeListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnFavoriteListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnFollowListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnStatusListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnUnBlockListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnUnFavoriteListener;
import jp.ac.kcg.projectexercises.twitter.client.event.OnUnFollowListener;

/**
 */
public interface Stream {
    /**
     * OnStatusListenerを追加する
     *
     * @param listener OnStatusListener
     * @return 自身のインスタンス
     */
    Stream addOnStatusListener(OnStatusListener listener);

    /**
     * OnDeletionNoticeListenerを追加する
     *
     * @param listener OnDeletionNoticeListener
     * @return 自身のインスタンス
     */
    Stream addOnDeletionNoticeListener(OnDeletionNoticeListener listener);

    /**
     * OnFavoriteListenerを追加する
     *
     * @param listener OnFavoriteListener
     * @return 自身のインスタンス
     */
    Stream addOnFavoriteListener(OnFavoriteListener listener);

    /**
     * OnUnFavoriteListenerを追加する
     *
     * @param listener OnUnFavoriteListener
     * @return 自身のインスタンス
     */
    Stream addOnUnFavoriteListener(OnUnFavoriteListener listener);

    /**
     * OnFollowListenerを追加する
     *
     * @param listener OnFollowListener
     * @return 自身のインスタンス
     */
    Stream addOnFollowListener(OnFollowListener listener);

    /**
     * OnUnFollowListenerを追加する
     *
     * @param listener OnUnFollowListener
     * @return 自身のインスタンス
     */
    Stream addOnUnFollowListener(OnUnFollowListener listener);

    /**
     * OnBlockListenerを追加する
     *
     * @param listener OnBlockListener
     * @return 自身のインスタンス
     */
    Stream addOnBlockListener(OnBlockListener listener);

    /**
     * OnUnBlockListenerを追加する
     *
     * @param listener OnUnBlockListener
     * @return 自身のインスタンス
     */
    Stream addOnUnBlockListener(OnUnBlockListener listener);

    /**
     * OnStatusListenerをリムーブする
     *
     * @param listener リムーブしたいOnStatusListener
     * @return 自身のインスタンス
     */
    Stream removeOnStatusListener(OnStatusListener listener);

    /**
     * OnDeletionNoticeListenerをリムーブする
     *
     * @param listener リムーブしたいOnDeletionNoticeListener
     * @return 自身のインスタンス
     */
    Stream removeOnDeletionNoticeListener(OnDeletionNoticeListener listener);

    /**
     * OnFavoriteListenerをリムーブする
     *
     * @param listener リムーブしたいOnFavoriteListener
     * @return 自身のインスタンス
     */
    Stream removeOnFavoriteListener(OnFavoriteListener listener);

    /**
     * OnUnFavoriteListenerをリムーブする
     *
     * @param listener リムーブしたいOnUnFavoriteListener
     * @return 自身のインスタンス
     */
    Stream removeOnUnFavoriteListener(OnUnFavoriteListener listener);

    /**
     * OnFollowListenerをリムーブする
     *
     * @param listener リムーブしたいOnFollowListener
     * @return 自身のインスタンス
     */
    Stream removeOnFollowListener(OnFollowListener listener);

    /**
     * OnUnFavoriteListenerをリムーブする
     *
     * @param listener リムーブしたいOnUnFavoriteListener
     * @return 自身のインスタンス
     */
    Stream removeOnUnFollowListener(OnUnFavoriteListener listener);

    /**
     * OnBlockListenerをリムーブする
     *
     * @param listener リムーブしたいOnBlockListener
     * @return 自身のインスタンス
     */
    Stream removeOnBlockListener(OnBlockListener listener);

    /**
     * OnUnBlockListenerをリムーブする
     *
     * @param listener リムーブしたいOnUnBlockListener
     * @return 自身のインスタンス
     */
    Stream removeOnUnBlockListener(OnUnBlockListener listener);
}
