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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image


typealias CallbackGetRestoreSite = (MutableList<SRestoreSite>)->Unit
typealias CallbackGetPicture = (MutableList<SPicture>)->Unit
typealias CallbackGetCompare = (MutableList<SComparePicture>)->Unit
typealias CallbackCheckExist = (Boolean)->Unit
typealias CallbackImage = (MutableList<Bitmap>)->Unit

public enum class EPicType(var type:Int)
{
    undefined(0)
}

public data class SRestoreSite(var objectId:String = ""
                                , var site_name:String = "not defined name"
                                , var information:String = "not defined information"
                                , var est_year:Int = 0
                                , var restore_year:Int = 0
                                , var address:String = "not defeined address"
                                , var coordinate_x:Double = 0.0
                                , var coordinate_y:Double = 0.0
                                )

public data class SPicture(var objectId:String = ""
                            , var file:Picture
                            , var image_name:String = ""
                            , var site_id:String = ""
                            , var pic_type:EPicType = EPicType.undefined
                            )

public data class SComparePicture(var objectId:String = ""
                                    , var oldPic_id:String = ""
                                    , var newPic_id:String = ""
                                    , var site_id:String = ""
                                    , var description:String = ""
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

    companion object TestData{
        var listImageTest:MutableList<Bitmap> = mutableListOf()
    }

    var bIsUpdatedSite:Boolean = false;
    var bIsUpdatedPicture:Boolean = false;
    var bIsUpdatedCompare:Boolean = false;

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
        returnRestoreSite = mutableListOf(SRestoreSite(objectId = objectId))

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
                    returnRestoreSite[0].coordinate_y = `object`.getDouble("coodinate_y")

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

    private fun getSiteListFromParse(objectList: List<ParseObject>?)
    {
        lateinit var obj:ParseObject
        if (objectList != null) {
            for(obj in objectList){
                this.returnRestoreSite.add(SRestoreSite(
                    obj.getString("objectId").toString()
                    , obj.getString("site_name").toString()
                    , obj.getString("information").toString()
                    , obj.getInt("est_year")
                    , obj.getInt("restore_year")
                    , obj.getString("address").toString()
                    , obj.getDouble("coordinate_x")
                    , obj.getDouble("coodinate_y")
                ))
            }

            this.pCallbackSite(this.returnRestoreSite);
        }
        else
        {
            Log.d("Parse", "Error: objectList null")
        }
    }

    fun addRestoreSite(oSite:SRestoreSite, pCallback: CallbackGetRestoreSite):Unit{
        val newSiteObject = ParseObject("RestoreSite")

        newSiteObject.put("site_name", oSite.site_name)
        newSiteObject.put("information", oSite.information)
        newSiteObject.put("est_year", oSite.est_year)
        newSiteObject.put("restore_year", oSite.restore_year.toString())
        newSiteObject.put("address", oSite.address)
        newSiteObject.put("coordinate_x", oSite.coordinate_x)
        newSiteObject.put("coodinate_y", oSite.coordinate_y)

        newSiteObject.saveInBackground { e ->

            if (e == null) {
                //We saved the object and fetching data again
                if(pCallback != null)
                {
                    pCallback(mutableListOf(oSite))
                }
            } else {
                //We have an error.We are showing error message here.
                Log.d("Parse", "Error: " + e.message)
            }
        }
    }

    fun getAllRestoreSite(pCallback: CallbackGetRestoreSite):Unit
    {
        this.returnRestoreSite = mutableListOf(SRestoreSite())

        var query = ParseQuery.getQuery<ParseObject>("RestoreSite")
        query.orderByAscending("site_name");
        if(this.bIsUpdatedSite)
        {
            query.fromLocalDatastore();
        }

        if(pCallback != null) {
            pCallbackSite = pCallback;

            query.findInBackground { objectList: List<ParseObject>?, e: ParseException? ->
                if (e == null) {
                    Log.d("Parse", "Retrieved " + objectList?.size + " Site")
                    this.bIsUpdatedSite = true;
                    this.getSiteListFromParse(objectList);
                } else {
                    Log.d("Parse", "Error: " + e.message)
                }
            }
        }

        return;
    }

    fun getAllRestoreSiteByName(siteName:String, pCallback: CallbackGetRestoreSite):Unit
    {
        this.returnRestoreSite = mutableListOf(SRestoreSite())

        var query = ParseQuery.getQuery<ParseObject>("RestoreSite")
        query.whereEqualTo("site_name", siteName);
        if(this.bIsUpdatedSite)
        {
            query.fromLocalDatastore();
        }

        if(pCallback != null) {
            pCallbackSite = pCallback;

            query.findInBackground { objectList: List<ParseObject>?, e: ParseException? ->
                //progressDialog!!.hide()
                if (e == null) {
                    Log.d("Parse", "Retrieved " + objectList?.size + " Site")
                    lateinit var obj:ParseObject
                    if (objectList != null) {
                        this.getSiteListFromParse(objectList);
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


    fun isSiteNameExist(siteName:String, pCallback:CallbackCheckExist)
    {
        var query = ParseQuery.getQuery<ParseObject>("RestoreSite")
        query.whereEqualTo("site_name", siteName);
        if(this.bIsUpdatedSite)
        {
            query.fromLocalDatastore();
        }

        if(pCallback != null) {

            query.findInBackground { objectList: List<ParseObject>?, e: ParseException? ->
                //progressDialog!!.hide()
                if (e == null) {
                    Log.d("Parse", "Retrieved " + objectList?.size + " Site")

                    if (objectList != null) {
                        var bExist = false;
                        if(objectList.isNotEmpty())
                        {
                            bExist = true;
                        }

                        pCallback(bExist);
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

    fun getComparePictureBySite(siteName: String)
    {

    }

    fun getAllPicture(pCallback:CallbackImage)
    {
        //this.returnRestoreSite = mutableListOf(SRestoreSite())

        var query = ParseQuery.getQuery<ParseObject>("Picture")
        query.orderByAscending("site_id");
 //       if(this.bIsUpdatedSite)
//        {
//            query.fromLocalDatastore();
//        }

        if(pCallback != null) {
            //pCallbackSite = pCallback;

            query.findInBackground { objectList: List<ParseObject>?, e: ParseException? ->
                if (e == null) {
                    Log.d("Parse", "Retrieved Image" + objectList?.size + " Site")

                    var listImage:MutableList<Bitmap> = mutableListOf()

                    if (objectList != null) {
                        for(obj in objectList) {
                            //obj.getParseFile("file").toString()
                            val bitmap = BitmapFactory.decodeStream(obj.getParseFile("file").toString().byteInputStream())

                            if(bitmap != null) {
                                listImage.add(bitmap)
                            }
                        }

                        TestData.listImageTest = listImage
                        pCallback(listImage)
                    }
                    //this.bIsUpdatedSite = true;
                    //this.getSiteListFromParse(objectList);
                } else {
                    Log.d("Parse", "Error: " + e.message)
                }
            }
        }

        return;
    }


}