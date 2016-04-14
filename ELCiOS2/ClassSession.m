//
//  ClassSession.m
//  RollCall
//
//  Created by Eric Wang on 4/7/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "ClassSession.h"

@implementation ClassSession

- (id)initWithUUID:(NSUUID *)uuid
      withMajorValue:(uint16_t)majorValue
      withMinorValue:(uint16_t)minorValue
       withClassName:(NSString *)className
  withInstructorName:(NSString *)instructorName
       withStartTime:(NSString *)startTime
         withEndTime:(NSString *)endTime
            withDate:(NSString *)date
         withELCRoom:(NSString *)ELCRoom {
    _uuid = uuid;
    _majorValue = majorValue;
    _minorValue = minorValue;
    _className = className;
    _instructorName = instructorName;
    _startTime = startTime;
    _endTime = endTime;
    _date = date;
    _ELCRoom = ELCRoom;
    return self;
}

- (id)initWithClassName:(NSString *)className
  withInstructorName:(NSString *)instructorName
       withStartTime:(NSString *)startTime
         withEndTime:(NSString *)endTime
            withDate:(NSString *)date
         withELCRoom:(NSString *)ELCRoom {
    _className = className;
    _instructorName = instructorName;
    _startTime = startTime;
    _endTime = endTime;
    _date = date;
    _ELCRoom = ELCRoom;
    return self;
}

@end
