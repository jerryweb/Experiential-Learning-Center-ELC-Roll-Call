//
//  ClassSession.h
//  RollCall
//
//  Created by Eric Wang on 4/7/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ClassSession : NSObject

@property (strong, nonatomic, readonly) NSUUID *uuid;
@property (assign, nonatomic, readonly) uint16_t majorValue;
@property (assign, nonatomic, readonly) uint16_t minorValue;
@property (nonatomic,strong) NSString *className;
@property (nonatomic,strong) NSString *instructorName;
@property (nonatomic,strong) NSString *startTime;
@property (nonatomic,strong) NSString *endTime;
@property (nonatomic,strong) NSString *date;
@property (nonatomic,strong) NSString *ELCRoom;

- (id)initWithUUID:(NSUUID *)uuid
      withMajorValue:(uint16_t)majorValue
      withMinorValue:(uint16_t)minorValue
       withClassName:(NSString *)className
  withInstructorName:(NSString *)instructorName
       withStartTime:(NSString *)startTime
         withEndTime:(NSString *)endTime
            withDate:(NSString *)date
         withELCRoom:(NSString *)ELCRoom;

- (id)initWithClassName:(NSString *)className
withInstructorName:(NSString *)instructorName
withStartTime:(NSString *)startTime
withEndTime:(NSString *)endTime
withDate:(NSString *)date
              withELCRoom:(NSString *)ELCRoom;

@end