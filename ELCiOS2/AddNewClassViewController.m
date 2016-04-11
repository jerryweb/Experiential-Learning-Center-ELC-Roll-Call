//
//  AddNewClassViewController.m
//  RollCall
//
//  Created by Eric Wang on 4/10/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "AddNewClassViewController.h"

@interface AddNewClassViewController () <UITextFieldDelegate>

@property (nonatomic,strong) UITextField *UUIDField;
@property (nonatomic,strong) UITextField *classNameField;
@property (nonatomic,strong) UITextField *firstNameField;
@property (nonatomic,strong) UITextField *lastNameField;
@property (nonatomic,strong) UITextField *emailField;
@property (nonatomic,strong) UILabel *startLabel;
@property (nonatomic,strong) UILabel *endLabel;
@property (nonatomic,strong) UIDatePicker *startTime;
@property (nonatomic,strong) UIDatePicker *endTime;
@property (nonatomic,strong) UIButton *submitButton;
@property (nonatomic,strong) UIButton *cancelButton;

@end

@implementation AddNewClassViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _UUIDField = [[UITextField alloc] initWithFrame:CGRectMake(35, 90, 300, 30)];
    _UUIDField.text = @" UUID";
    _UUIDField.textColor = [UIColor whiteColor];
    _UUIDField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _UUIDField.delegate = self;
    _UUIDField.tintColor = [UIColor clearColor];
    _UUIDField.layer.borderWidth = .5f;
    _UUIDField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _UUIDField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _classNameField = [[UITextField alloc] initWithFrame:CGRectMake(35, 135, 300, 30)];
    _classNameField.text = @" Class Name";
    _classNameField.textColor = [UIColor whiteColor];
    _classNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _classNameField.delegate = self;
    _classNameField.tintColor = [UIColor clearColor];
    _classNameField.layer.borderWidth = .5f;
    _classNameField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _classNameField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _firstNameField = [[UITextField alloc] initWithFrame:CGRectMake(35, 180, 300, 30)];
    _firstNameField.text = @" Instructor First Name";
    _firstNameField.textColor = [UIColor whiteColor];
    _firstNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _firstNameField.delegate = self;
    _firstNameField.tintColor = [UIColor clearColor];
    _firstNameField.layer.borderWidth = .5f;
    _firstNameField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _firstNameField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _lastNameField = [[UITextField alloc] initWithFrame:CGRectMake(35, 225, 300, 30)];
    _lastNameField.text = @" Instructor Last Name";
    _lastNameField.textColor = [UIColor whiteColor];
    _lastNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _lastNameField.delegate = self;
    _lastNameField.tintColor = [UIColor clearColor];
    _lastNameField.layer.borderWidth = .5f;
    _lastNameField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _lastNameField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _emailField = [[UITextField alloc] initWithFrame:CGRectMake(35, 270, 300, 30)];
    _emailField.text = @" Instructor Email Address";
    _emailField.textColor = [UIColor whiteColor];
    _emailField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _emailField.delegate = self;
    _emailField.tintColor = [UIColor clearColor];
    _emailField.layer.borderWidth = .5f;
    _emailField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _emailField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _startLabel = [[UILabel alloc] initWithFrame:CGRectMake(35, 305, 100, 50)];
    _startLabel.text = @"Start Time";
    _startLabel.textColor = [UIColor whiteColor];
    _startLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    
    _startTime = [[UIDatePicker alloc] initWithFrame:CGRectMake(150, 305, 220, 50)];
    _startTime.datePickerMode = UIDatePickerModeTime;
    [_startTime setValue:[UIColor whiteColor] forKey:@"textColor"];
    
    _endLabel = [[UILabel alloc] initWithFrame:CGRectMake(35, 355, 100, 50)];
    _endLabel.text = @"End Time";
    _endLabel.textColor = [UIColor whiteColor];
    _endLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    
    _endTime = [[UIDatePicker alloc] initWithFrame:CGRectMake(150, 355, 220, 50)];
    _endTime.datePickerMode = UIDatePickerModeTime;
    [_endTime setValue:[UIColor whiteColor] forKey:@"textColor"];
    
    _submitButton = [[UIButton alloc] initWithFrame:CGRectMake(35, 410, 300, 30)];
    [_submitButton setTitle:@"Submit" forState:UIControlStateNormal];
    [_submitButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_submitButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _submitButton.layer.cornerRadius = 10;
    _submitButton.clipsToBounds = YES;
    [_submitButton addTarget:self action:@selector(submitNewClass:) forControlEvents:UIControlEventTouchUpInside];
    
    _cancelButton = [[UIButton alloc] initWithFrame:CGRectMake(35, 450, 300, 30)];
    [_cancelButton setTitle:@"Cancel" forState:UIControlStateNormal];
    [_cancelButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_cancelButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _cancelButton.layer.cornerRadius = 10;
    _cancelButton.clipsToBounds = YES;
    [_cancelButton addTarget:self action:@selector(cancelNewClass:) forControlEvents:UIControlEventTouchUpInside];
    
    [self.view addSubview:_UUIDField];
    [self.view addSubview:_classNameField];
    [self.view addSubview:_firstNameField];
    [self.view addSubview:_lastNameField];
    [self.view addSubview:_emailField];
    [self.view addSubview:_startLabel];
    [self.view addSubview:_startTime];
    [self.view addSubview:_endLabel];
    [self.view addSubview:_endTime];
    [self.view addSubview:_submitButton];
    [self.view addSubview:_cancelButton];
}

#pragma mark - button selectors

- (void)submitNewClass:(UIButton *)sender {
    [self.navigationController popViewControllerAnimated:NO];
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"Class Added" message:@"Successfully Added to Class List" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *alertAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:nil];
    [alertController addAction:alertAction];
    [self presentViewController:alertController animated:YES completion:nil];
}

- (void)cancelNewClass:(UIButton *)sender {
    [self.navigationController popViewControllerAnimated:NO];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    [textField setText:@""];
    textField.autocorrectionType = UITextAutocorrectionTypeNo;
}


@end
