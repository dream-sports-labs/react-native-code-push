import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  // Configuration
  getConfiguration(): Promise<{
    appVersion: string;
    clientUniqueId: string;
    deploymentKey: string;
    serverUrl: string;
    packageHash?: string;
  }>;

  // Update management
  downloadUpdate(
    updatePackage: Object,
    notifyProgress: boolean,
  ): Promise<Object>;
  
  installUpdate(
    updatePackage: Object,
    installMode: number,
    minimumBackgroundDuration: number,
  ): Promise<string>;

  getUpdateMetadata(updateState: number): Promise<Object | null>;

  // Application lifecycle
  restartApp(onlyIfUpdateIsPending: boolean): Promise<void>;
  notifyApplicationReady(): Promise<string>;
  
  // Restart control
  allow(): Promise<void>;
  disallow(): Promise<void>;
  clearPendingRestart(): Promise<void>;

  // Update status and rollback
  isFirstRun(packageHash: string): Promise<boolean>;
  isFailedUpdate(packageHash: string): Promise<boolean>;
  getLatestRollbackInfo(): Promise<Object | null>;
  setLatestRollbackInfo(packageHash: string): Promise<void>;

  // Telemetry
  getNewStatusReport(): Promise<Object | string>;
  recordStatusReported(statusReport: Object): void;
  saveStatusReportForRetry(statusReport: Object): void;

  // Development/testing
  downloadAndReplaceCurrentBundle(remoteBundleUrl: string): void;
  clearUpdates(): void;

  // Event emitter support
  addListener(eventName: string): void;
  removeListeners(count: number): void;

  // Constants
  getConstants(): {
    codePushInstallModeImmediate: number;
    codePushInstallModeOnNextRestart: number;
    codePushInstallModeOnNextResume: number;
    codePushInstallModeOnNextSuspend: number;
    codePushUpdateStateRunning: number;
    codePushUpdateStatePending: number;
    codePushUpdateStateLatest: number;
  };
}

export default TurboModuleRegistry.getEnforcing<Spec>('CodePush'); 