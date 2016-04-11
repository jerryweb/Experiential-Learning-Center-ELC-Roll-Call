//
//  ClassSession.m
//  RollCall
//
//  Created by Eric Wang on 4/7/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "ClassSession.h"

@implementation ClassSession

static NSString * const kRWTItemNameKey = @"name";
static NSString * const kRWTItemUUIDKey = @"uuid";
static NSString * const kRWTItemMajorValueKey = @"major";
static NSString * const kRWTItemMinorValueKey = @"minor";

- (instancetype)initWithName:(NSString *)name
                        uuid:(NSUUID *)uuid
                       major:(uint16_t)major
                       minor:(uint16_t)minor
{
    self = [super init];
    if (!self) {
        return nil;
    }
    
    _name = name;
    _uuid = uuid;
    _majorValue = major;
    _minorValue = minor;
    
    return self;
}

#pragma mark - NSCoding

- (instancetype)initWithCoder:(NSCoder *)aDecoder {
    self = [super init];
    if (!self) {
        return nil;
    }
    
    _name = [aDecoder decodeObjectForKey:kRWTItemNameKey];
    _uuid = [aDecoder decodeObjectForKey:kRWTItemUUIDKey];
    _majorValue = [[aDecoder decodeObjectForKey:kRWTItemMajorValueKey] unsignedIntegerValue];
    _minorValue = [[aDecoder decodeObjectForKey:kRWTItemMinorValueKey] unsignedIntegerValue];
    
    return self;
}

- (void)encodeWithCoder:(NSCoder *)aCoder {
    [aCoder encodeObject:self.name forKey:kRWTItemNameKey];
    [aCoder encodeObject:self.uuid forKey:kRWTItemUUIDKey];
    [aCoder encodeObject:[NSNumber numberWithUnsignedInteger:self.majorValue] forKey:kRWTItemMajorValueKey];
    [aCoder encodeObject:[NSNumber numberWithUnsignedInteger:self.minorValue] forKey:kRWTItemMinorValueKey];
}

@end
