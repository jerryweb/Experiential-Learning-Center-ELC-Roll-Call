//
//  SignupViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/26/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "SignupViewController.h"
#import "StudentHomepageViewController.h"

@interface SignupViewController () <UITextFieldDelegate>

@property (nonatomic,strong) UILabel *titleLabel;
@property (nonatomic,strong) UITextField *firstNameField;
@property (nonatomic,strong) UITextField *lastNameField;
@property (nonatomic,strong) UITextField *emailField;
@property (nonatomic,strong) UITextField *universityIDField;
@property (nonatomic,strong) UITextField *passwordField;
@property (nonatomic,strong) UITextField *confirmPasswordField;
@property (nonatomic,strong) UIButton *confirmButton;
@property (nonatomic,strong) UIButton *cancelButton;

@end

@implementation SignupViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // config title
    _titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(126, 100, 123, 43)];
    _titleLabel.text = @"RollCall";
    _titleLabel.textColor = [UIColor whiteColor];
    _titleLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:36];
    
    // config first name field
    _firstNameField = [[UITextField alloc] initWithFrame:CGRectMake(38, 213, 120, 23)];
    _firstNameField.text = @"First Name";
    _firstNameField.textColor = [UIColor whiteColor];
    _firstNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _firstNameField.delegate = self;
    _firstNameField.tintColor = [UIColor clearColor];
    
    // config last name field
    _lastNameField = [[UITextField alloc] initWithFrame:CGRectMake(38, 266, 120, 23)];
    _lastNameField.text = @"Last Name";
    _lastNameField.textColor = [UIColor whiteColor];
    _lastNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _lastNameField.delegate = self;
    _lastNameField.tintColor = [UIColor clearColor];
    
    // config email field
    _emailField = [[UITextField alloc] initWithFrame:CGRectMake(38, 319, 140, 23)];
    _emailField.text = @"Email Address";
    _emailField.textColor = [UIColor whiteColor];
    _emailField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _emailField.delegate = self;
    _emailField.tintColor = [UIColor clearColor];
    
    // config university id field
    _universityIDField = [[UITextField alloc] initWithFrame:CGRectMake(38, 372, 160, 23)];
    _universityIDField.text = @"University ID #";
    _universityIDField.textColor = [UIColor whiteColor];
    _universityIDField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _universityIDField.delegate = self;
    _universityIDField.tintColor = [UIColor clearColor];
    
    // config password field
    _passwordField = [[UITextField alloc] initWithFrame:CGRectMake(38, 425, 160, 23)];
    _passwordField.text = @"Password";
    _passwordField.textColor = [UIColor whiteColor];
    _passwordField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _passwordField.delegate = self;
    _passwordField.tintColor = [UIColor clearColor];
    _passwordField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    // config password confirm field
    _confirmPasswordField = [[UITextField alloc] initWithFrame:CGRectMake(38, 478, 180, 23)];
    _confirmPasswordField.text = @"Confirm Password";
    _confirmPasswordField.textColor = [UIColor whiteColor];
    _confirmPasswordField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _confirmPasswordField.delegate = self;
    _confirmPasswordField.tintColor = [UIColor clearColor];
    _confirmPasswordField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    // config create button
    _confirmButton = [[UIButton alloc] initWithFrame:CGRectMake(38, 521, 140, 36)];
    [_confirmButton setTitle:@"Create Account" forState:UIControlStateNormal];
    [_confirmButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_confirmButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _confirmButton.titleLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:14];
    _confirmButton.layer.cornerRadius = 10;
    _confirmButton.clipsToBounds = YES;
    [_confirmButton addTarget:self action:@selector(createAccount:) forControlEvents:UIControlEventTouchUpInside];
    
    // config cancel button
    _cancelButton = [[UIButton alloc] initWithFrame:CGRectMake(198, 521, 140, 36)];
    [_cancelButton setTitle:@"Cancel" forState:UIControlStateNormal];
    [_cancelButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_cancelButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _cancelButton.titleLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:14];
    _cancelButton.layer.cornerRadius = 10;
    _cancelButton.clipsToBounds = YES;
    [_cancelButton addTarget:self action:@selector(cancelSignup:) forControlEvents:UIControlEventTouchUpInside];
    
    [self.view addSubview:_titleLabel];
    [self.view addSubview:_firstNameField];
    [self.view addSubview:_lastNameField];
    [self.view addSubview:_emailField];
    [self.view addSubview:_universityIDField];
    [self.view addSubview:_passwordField];
    [self.view addSubview:_confirmPasswordField];
    [self.view addSubview:_confirmButton];
    [self.view addSubview:_cancelButton];
    
    // draw and add dividers
    UIView *dividerOne = [[UIView alloc] initWithFrame:CGRectMake(38, 251, 300, .4)];
    dividerOne.backgroundColor = [UIColor whiteColor];
    UIView *dividerTwo = [[UIView alloc] initWithFrame:CGRectMake(38, 304, 300, .4)];
    dividerTwo.backgroundColor = [UIColor whiteColor];
    UIView *dividerThree = [[UIView alloc] initWithFrame:CGRectMake(38, 357, 300, .4)];
    dividerThree.backgroundColor = [UIColor whiteColor];
    UIView *dividerFour = [[UIView alloc] initWithFrame:CGRectMake(38, 410, 300, .4)];
    dividerFour.backgroundColor = [UIColor whiteColor];
    UIView *dividerFive = [[UIView alloc] initWithFrame:CGRectMake(38, 463, 300, .4)];
    dividerFive.backgroundColor = [UIColor whiteColor];
    
    [self.view addSubview:dividerOne];
    [self.view addSubview:dividerTwo];
    [self.view addSubview:dividerThree];
    [self.view addSubview:dividerFour];
    [self.view addSubview:dividerFive];
}

- (void)cancelSignup:(UIButton *)sender {
    [self.navigationController popToRootViewControllerAnimated:NO];
}

- (void)createAccount:(UIButton *)sender {
    StudentHomePageViewController *svc = [[StudentHomePageViewController alloc] init];
    [self.navigationController pushViewController:svc animated:NO];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    [textField setText:@""];
    textField.autocorrectionType = UITextAutocorrectionTypeNo;
    if(textField == _passwordField) {
        _passwordField.secureTextEntry = YES;
    } else if(textField == _confirmPasswordField) {
        _confirmPasswordField.secureTextEntry = YES;
    }
}

@end
