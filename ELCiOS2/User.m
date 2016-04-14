//
//  User.m
//  RollCall
//
//  Created by Eric Wang on 4/13/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "User.h"

@implementation User {
    BOOL isAdmin;
}

- (void)initWithName:(NSString *)name
           withEmail:(NSString *)email
 withAttendedClasses:(NSMutableArray *)attendedClasses
     withAdminStatus:(BOOL)isAdmin {
    _name = name;
    _email = email;
    _attendedClasses = attendedClasses;
    isAdmin = isAdmin;
}

@end
