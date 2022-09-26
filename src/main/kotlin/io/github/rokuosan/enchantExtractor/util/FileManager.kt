package io.github.rokuosan.enchantExtractor.util

import java.nio.file.Files
import java.nio.file.NoSuchFileException
import kotlin.io.path.Path


enum class FMResults{
    CREATED,
    NO_PARENT_DIRECTORY,
    ALREADY_EXISTS,
    AN_EXCEPTION_OCCURRED
}

object FileManager {
    private const val ROOT_DIR = "./plugins/ServerWebManager"


    /**
     * 新たにディレクトリを作成します。
     * ディレクトリ名にnull を指定した場合、プラグインと同じ名前のディレクトリを作成します。
     *
     * @param name 作成するディレクトリ名
     * @param recursive 再帰的にフォルダを作成するか
     */
    fun createDir(name: String?, recursive: Boolean = false): FMResults{
        name?: run{
            return try{
                Files.createDirectory(Path(ROOT_DIR))
                FMResults.CREATED
            }catch(e: Exception){
                FMResults.AN_EXCEPTION_OCCURRED
            }
        }


        // 名前の合成
        val dir = "$ROOT_DIR/$name"

        // ディレクトリがすでに存在する場合
        if(isExists(dir)) return FMResults.ALREADY_EXISTS

        // ディレクトリ作成
        return try{
            if(recursive){
                Files.createDirectories(Path(dir))
            }else{
                Files.createDirectory(Path(dir))
            }

            FMResults.CREATED
        }catch(e: NoSuchFileException){
            FMResults.NO_PARENT_DIRECTORY
        }catch(e: Exception){
            FMResults.AN_EXCEPTION_OCCURRED
        }
    }

    /**
     * ファイルを新規作成する関数。
     *
     * @param name ファイル名
     */
    fun createFile(name: String): FMResults{
        //名前の合成
        val dir = "$ROOT_DIR/$name"

        // ファイルがすでに存在する場合
        if(isExists(dir)) return FMResults.ALREADY_EXISTS

        //　ファイル作成
        return try {
            Files.createFile(Path(dir))

            FMResults.CREATED
        }catch(e: FileAlreadyExistsException){
            FMResults.ALREADY_EXISTS
        }catch(e: Exception){
            FMResults.AN_EXCEPTION_OCCURRED
        }
    }

    fun isExists(name: String): Boolean{
        return try {
            Files.exists(Path("$ROOT_DIR/$name"))
        } catch(e: Exception) {
            false
        }
    }
}