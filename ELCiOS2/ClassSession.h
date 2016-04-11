//
//  ClassSession.h
//  RollCall
//
//  Created by Eric Wang on 4/7/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ClassSession : NSObject <NSCoding>

@property (strong, nonatomic, readonly) NSString *name;
@property (strong, nonatomic, readonly) NSUUID *uuid;
@property (assign, nonatomic, readonly) uint16_t majorValue;
@property (assign, nonatomic, readonly) uint16_t minorValue;

- (instancetype)initWithName:(NSString *)name
                        uuid:(NSUUID *)uuid
                       major:(uint16_t)major
                       minor:(uint16_t)minor;

@end
