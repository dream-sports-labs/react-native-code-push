#import "CodePush.h"

#if __has_include(<React/RCTConvert.h>)
#import <React/RCTConvert.h>
#elif __has_include("RCTConvert.h")
#import "RCTConvert.h"
#else
// Fallback for standalone library builds
@interface RCTConvert : NSObject
+ (NSInteger)NSInteger:(id)json;
@end
#define RCT_ENUM_CONVERTER(type, values, default, getter) \
+ (type)type:(id)json { return default; }
#endif

// Extending the RCTConvert class allows the React Native
// bridge to handle args of type "CodePushInstallMode"
@implementation RCTConvert (CodePushInstallMode)

RCT_ENUM_CONVERTER(CodePushInstallMode, (@{ @"codePushInstallModeImmediate": @(CodePushInstallModeImmediate),
                                            @"codePushInstallModeOnNextRestart": @(CodePushInstallModeOnNextRestart),
                                            @"codePushInstallModeOnNextResume": @(CodePushInstallModeOnNextResume),
                                            @"codePushInstallModeOnNextSuspend": @(CodePushInstallModeOnNextSuspend) }),
                   CodePushInstallModeImmediate, // Default enum value
                   integerValue)

@end
