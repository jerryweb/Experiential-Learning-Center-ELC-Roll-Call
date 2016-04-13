//
//  RollCallNavigationController.m
//  RollCall
//
//  Created by Eric Wang on 3/20/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "RollCallNavigationController.h"
#import "LoginViewController.h"

@interface RollCallNavigationController ()

@end

@implementation RollCallNavigationController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationBar.hidden = YES;
     LoginViewController *lvc = [[LoginViewController alloc] init];
    self.viewControllers = @[lvc];
}

@end