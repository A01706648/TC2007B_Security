package com.itesm.esenciapatrimonio

import android.app.Application
import android.graphics.Picture
import android.util.Log
import com.parse.Parse
import com.parse.ParseObject
import com.parse.GetCallback
import com.parse.ParseQuery
import com.parse.ParseException
import android.content.Context


typealias CallbackGetRestoreSite = (MutableList<SRestoreSite>)->Unit
typealias CallbackGetPicture = (MutableList<SPicture>)->Unit
typealias CallbackGetCompare = (MutableList<SComparePicture>)->Unit

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
    lateinit var returnRestoreSite:MutableList<SRestoreSite>;
    lateinit var returnPicture:MutableList<SPicture>;
    lateinit var returnComparePicture:MutableList<SComparePicture>;
    lateinit var pCallbackSite:CallbackGetRestoreSite;
    lateinit var pCallbackPicture:CallbackGetPicture;
    lateinit var pCallbackCompare:CallbackGetCompare;

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

    fun getPicture(objectId: String, callback: CallbackGetRestoreSite):Unit
    {
        var returnPicture:SPicture = SPicture(objectId, Picture(), "","", EPicType.undefined);


    }

    fun getRestoreSite(objectId: String, pCallback: CallbackGetRestoreSite):Unit
    {
        returnRestoreSite = mutableListOf(SRestoreSite(
            objectId
            , ""
            ,""
            ,0
            ,0
            ,""
            ,0.0
            , 0.0))

        var query = ParseQuery.getQuery<ParseObject>("RestoreSite")

        if(pCallback != null) {
            pCallbackSite = pCallback;

            query.getInBackground(objectId) { `object`, e ->
                if (e == null) {
                    // object will be your game score
                    returnRestoreSite[0].site_name = `object`.getString("site_name").toString()
                    returnRestoreSite[0].information = `object`.getString("information").toString()
                    returnRestoreSite[0].est_year = `object`.getInt("est_year")
                    returnRestoreSite[0].restore_year = `object`.getInt("restore_year")
                    returnRestoreSite[0].address = `object`.getString("address").toString()
                    returnRestoreSite[0].coordinate_x = `object`.getDouble("coordinate_x")
                    returnRestoreSite[0].coordinate_y = `object`.getDouble("coordinate_y")

                    this.pCallbackSite(returnRestoreSite)

                } else {
                    // something went wrong
                }
            }
        }

        return;
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

    fun getAllRestoreSite(pCallback: CallbackGetRestoreSite):Unit
    {
        this.returnRestoreSite = mutableListOf()

        var query = ParseQuery.getQuery<ParseObject>("RestoreSite")
        //query.whereExists("site_name");
        query.orderByAscending("site_name");
        //progressDialog!!.show();

        if(pCallback != null) {
            pCallbackSite = pCallback;

            query.findInBackground { objectList: List<ParseObject>?, e: ParseException? ->
                //progressDialog!!.hide()
                if (e == null) {
                    Log.d("Parse", "Retrieved " + objectList?.size + " Site")
                    lateinit var obj:ParseObject
                    if (objectList != null) {
                        for(obj in objectList){
                            this.returnRestoreSite.add(SRestoreSite(
                                obj.getString("objectId").toString(), obj.getString("site_name").toString(), obj.getString("information").toString(), obj.getInt("est_year"), obj.getInt("restore_year"), obj.getString("address").toString(), obj.getDouble("coordinate_x"), obj.getDouble("coordinate_y")
                            ))
                        }

                        this.pCallbackSite(this.returnRestoreSite);
                    }
                    else
                    {
                        Log.d("Parse", "Error: objectList null")
                    }
                } else {
                    Log.d("Parse", "Error: " + e.message)
                }
            }
        }

        return;

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