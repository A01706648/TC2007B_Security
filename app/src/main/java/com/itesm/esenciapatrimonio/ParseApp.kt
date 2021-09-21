package com.itesm.esenciapatrimonio

import android.app.Application
import android.graphics.Picture
import android.util.Log
import com.parse.Parse
import com.parse.ParseObject
import com.parse.GetCallback
import com.parse.ParseQuery
import android.content.Context


public enum class EPicType(var type:Int)
{
    undefined(0)
}

public data class SRestoreSite(var objectId:String
                                , var site_name:String
                                , var information:String
                                , var est_year:Int
                                , var restore_year:Int
                                , var address:String
                                , var coordinate_x:Double
                                , var coordinate_y:Double
                                )

public data class SPicture(var objectId:String
                            , var file:Picture
                            , var image_name:String
                            , var site_id:String
                            , var pic_type:EPicType
                            )

public data class SComparePicture(var objectId:String
                                    , var oldPic_id:String
                                    , var newPic_id:String
                                    , var site_id:String
                                    , var description:String
                                    )

public class ParseApp /*: Application()*/ {
    /*
    override fun onCreate()
    {
        super.onCreate()


    }
*/
    fun initParse()
    {
        /*
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )



        Log.d("Parse","Init")
        */
    }

    fun getPicture(objectId: String):SPicture
    {
        var returnPicture:SPicture = SPicture(objectId, Picture(), "","", EPicType.undefined);

        return returnPicture;
    }

    fun getRestoreSite(objectId: String):SRestoreSite
    {
        var returnRestoreSite:SRestoreSite = SRestoreSite(
            objectId
            , ""
            ,""
            ,0
            ,0
            ,""
            ,0.0
            , 0.0)

        var query = ParseQuery.getQuery<ParseObject>("RestoreSite")
        query.getInBackground(objectId) {`object`, e ->
            if (e == null) {
                // object will be your game score
                returnRestoreSite.site_name = `object`.getString("site_name").toString()
                returnRestoreSite.information = `object`.getString("information").toString()
                returnRestoreSite.est_year = `object`.getInt("est_year")
                returnRestoreSite.restore_year = `object`.getInt("restore_year")
                returnRestoreSite.address = `object`.getString("address").toString()
                returnRestoreSite.coordinate_x = `object`.getDouble("coordinate_x")
                returnRestoreSite.coordinate_y = `object`.getDouble("coordinate_y")

            } else {
                // something went wrong
            }
        }

        return returnRestoreSite;
    }



    fun getComparePicture(objectId: String):SComparePicture
    {
        val returnComparePicture:SComparePicture = SComparePicture(
            objectId
            , ""
            , ""
            , ""
            , "");

        return returnComparePicture;
    }

    fun getAllPicture(objectId: String):List<SPicture>
    {
        val returnPicture:SPicture = SPicture("", Picture(), "","", EPicType.undefined);

        return listOf(returnPicture);
    }

    fun getAllRestoreSite():List<SRestoreSite>
    {
        val returnRestoreSite:SRestoreSite = SRestoreSite(
            ""
            , ""
            ,""
            ,0
            ,0
            ,""
            ,0.0
            , 0.0)

        return listOf(returnRestoreSite);
    }

    fun getAllComparePicture():List<SComparePicture>
    {
        val returnComparePicture:SComparePicture = SComparePicture(
            ""
            , ""
            , ""
            , ""
            , "");

        return listOf(returnComparePicture);
    }


}