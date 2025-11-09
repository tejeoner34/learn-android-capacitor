import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.tejeoner34.learnandroidcapacitor',
  appName: 'learn-android-capacitor',
  webDir: 'dist/learn-android-capacitor/browser',
  plugins: {
    SplashScreen: {
      launchShowDuration: 0,
      launchAutoHide: true,
      backgroundColor: '#343338',
      iosSpinnerStyle: 'large',
      showSpinner: true,
      spinnerColor: '#929349',
    },
  },
};

export default config;
