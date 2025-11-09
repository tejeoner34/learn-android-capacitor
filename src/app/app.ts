import { Component, inject, InjectionToken, OnInit, signal } from '@angular/core';
import { Device, DeviceInfo, DevicePlugin } from '@capacitor/device';
import { SplashScreen, SplashScreenPlugin } from '@capacitor/splash-screen';

export const DEVICE = new InjectionToken<DevicePlugin>('DevicePlugin');
export const SPLASH_SCREEN = new InjectionToken<SplashScreenPlugin>('SplashScreenPlugin');

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
  providers: [
    {
      provide: DEVICE,
      useValue: Device,
    },
    {
      provide: SPLASH_SCREEN,
      useValue: SplashScreen,
    },
  ],
})
export class App implements OnInit {
  private device = inject(DEVICE);
  protected readonly title = signal('learn-android-capacitor');
  info: DeviceInfo | null = null;

  async ngOnInit() {
    this.info = await this.device.getInfo();
    await SplashScreen.show({
      showDuration: 2000,
      autoHide: true,
    });
  }
}
