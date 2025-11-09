package com.tejeoner34.learnandroidcapacitor;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(
  name = "LocationInfo",
  permissions = {
    @Permission(strings = {Manifest.permission.ACCESS_FINE_LOCATION}, alias = "location")
  }
)
public class LocationInfoPlugin extends Plugin {

  @PluginMethod
  public void getCurrentPosition(PluginCall call) {
    Context ctx = getContext();
    LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

    // 権限チェック
    if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // 権限がない場合、要求する
      requestPermissionForAlias("location", call, "locationPermsCallback");
      return;
    }

    // GPS もしくはネットワークから最後の位置を取得
    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    if (location == null) {
      location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    if (location != null) {
      JSObject ret = new JSObject();
      ret.put("latitude", location.getLatitude());
      ret.put("longitude", location.getLongitude());
      call.resolve(ret);
    } else {
      call.reject("位置情報が取得できませんでした");
    }
  }

  @PermissionCallback
  private void locationPermsCallback(PluginCall call) {
    if (getPermissionState("location") == PermissionState.GRANTED) {
      getCurrentPosition(call);
    } else {
      call.reject("権限が拒否されました");
    }
  }
}

