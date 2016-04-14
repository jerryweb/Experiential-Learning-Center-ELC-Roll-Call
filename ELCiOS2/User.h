//
//  User.h
//  RollCall
//
//  Created by Eric Wang on 4/13/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface User : NSObject

@property (nonatomic,strong) NSString *name;
@property (nonatomic,strong) NSString *email;
@property (nonatomic,strong) NSMutableArray *attendedClasses;

- (void)initWithName:(NSString *)name
           withEmail:(NSString *)email
 withAttendedClasses:(NSMutableArray *)attendedClasses;

@end
