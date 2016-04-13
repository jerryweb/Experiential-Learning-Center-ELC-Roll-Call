//
//  AppDelegate.m
//  RollCall
//
//  Created by Eric Wang on 2/26/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "AppDelegate.h"
#import "RollCallNavigationController.h"
#import <Firebase/Firebase.h>

@interface AppDelegate ()

@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    self.window.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"background"]];
    [self.window setRootViewController:[[RollCallNavigationController alloc] init]];
    [self.window makeKeyAndVisible];
    return YES;
}

@end
