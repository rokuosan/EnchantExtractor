package io.github.rokuosan.enchantExtractor.util

import java.io.BufferedReader
import java.io.FileReader
import java.util.Properties

/**
 * プラグインを初期化するためのオブジェクト
 */
object Initializer {

    /**
     * 初期化処理を実行する関数
     *
     */
    fun run(){
        // プロジェクトのバージョンを取得
        val version = ResourceLoader().load("version")!!.first()

        // init ファイルがあるかどうか確認する
        if(FileManager.isExists("init")){
            // init ファイルをロード
            val prop = Properties()
            prop.load(BufferedReader(FileReader("init")))

            // バージョン比較
            if(prop.getProperty("version") == version){
                // バージョンが同じ場合は初期化処理をしない
                return
            }
        }

        // プロパティ
        val prop = Properties()

        // プラグインが使用するフォルダの作成
        FileManager.createDir(null)



    }
}